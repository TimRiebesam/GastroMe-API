package gastrome.api.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.PLZ;
import gastrome.api.entities.Restaurant;
import gastrome.api.entities.RestaurantJson;
import gastrome.api.entities.Standort;
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.entities.positionstack.PositionstackGeoApiData;
import gastrome.api.repositories.PLZRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.StandortRepository;
import gastrome.api.services.interfaces.GeoService;
import gastrome.api.services.interfaces.ImageService;
import gastrome.api.services.interfaces.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	GeoService geoService;
	
	@Autowired
	PLZRepository plzRepository;
	
	@Autowired
	StandortRepository standortRepository;
	
	@Autowired
	ImageService imageService;
	
	@Override
	public List<Restaurant> getAllRestaurants(HttpServletResponse response, Double lat, Double lng) throws IOException {
		if(lat != null && lng != null) {
			return getRestaurantsNearby(lat, lng);
		}
		
		else if((lat == null || lng == null) && !(lat == null && lng == null)) {
			response.sendError(400, "Parameter lat oder lng nicht vorhanden!");
			return null;
		}
		
		else {
			List<Restaurant> restaurants = restaurantRepository.findAll();
			if(!restaurants.isEmpty())
				return restaurants;
			
			response.sendError(404, "Kein Restaurants gefunden!");
			return null;
		}
	}

	private List<Restaurant> getRestaurantsNearby(Double lat, Double lng) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		GeonamesNearbyPostalCodesResponse geonamesResponse =  geoService.getNearbyPostalCodesByLatAndLng(lat, lng);
		
		List<Integer> postalCodes = new ArrayList<Integer>();
		geonamesResponse.getPostalCodes().forEach(geonamePostalCode -> {
			postalCodes.add(geonamePostalCode.getPostalCode());
		});
		
		List<PLZ> plzs = plzRepository.findAllByPlzIn(postalCodes);
		
		plzs.forEach(plz -> {
			plz.getStandorte().forEach(standort -> {
				restaurants.add(standort.getRestaurant());
			});
		});
		
		return restaurants;
	}

	@Override
	public Restaurant getRestaurant(UUID restaurantId, HttpServletResponse response) throws IOException {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if(restaurant == null)
			response.sendError(404, "Restaurant nicht gefunden!");
		
		return restaurant;
	}

	@Override
	public Restaurant updateRestaurant(UUID restaurantId, HttpServletResponse response, RestaurantJson restaurantJson) throws IOException {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if(restaurant == null) {
			response.sendError(404, "Restaurant nicht gefunden!");
			return null;
		}
		else {
			PositionstackGeoApiData locationData = geoService.getGeodataFromAddress(
					Integer.parseInt(restaurantJson.getPlz()), restaurantJson.getStrasse(), restaurantJson.getHausnummer());
			
			if(locationData != null) {
				restaurant.setName(restaurantJson.getName());
				restaurant.setBeschreibung(restaurantJson.getBeschreibung());
				restaurant.setEmail(restaurantJson.getEmail());
				if(!restaurantJson.getBild().equals(""))
					restaurant.setBild(imageService.base64StringToByteArray(restaurantJson.getBild()));
				
				PLZ plz = restaurant.getStandort().getPlz();
				plz.setPlz(Integer.parseInt(restaurantJson.getPlz()));
				plz.setStadt(restaurantJson.getStadt());
				plzRepository.save(plz);
				
				Standort standort = restaurant.getStandort();
				standort.setHausnummer("" + restaurantJson.getHausnummer());
				standort.setStrasse(restaurantJson.getStrasse());
				standort.setLaengengrad(locationData.getLongitude());
				standort.setBreitengrad(locationData.getLatitude());
				standort.setBeschreibung(locationData.getName() + "," + locationData.getPostal_code() + ", " + locationData.getRegion() + ", " + locationData.getCountry());
				standort.setPlz(plz);
				standortRepository.save(standort);
				
				return restaurantRepository.save(restaurant);
			} else {
				response.sendError(400, "Die übergebenen Adressdaten waren fehlerhaft oder konnten nicht in Längen- und Breitengrade übersetzt werden.");
				return null;
			}
		}
	}
	
}
