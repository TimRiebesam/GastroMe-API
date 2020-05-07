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
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.repositories.PLZRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.services.interfaces.GeoService;
import gastrome.api.services.interfaces.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	GeoService geoService;
	
	@Autowired
	PLZRepository plzRepository;
	
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
	
}
