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
import gastrome.api.entities.geocodexyz.GeocodeXyzApiResponse;
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.repositories.PLZRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.StandortRepository;
import gastrome.api.services.interfaces.GeoService;
import gastrome.api.services.interfaces.ImageService;
import gastrome.api.services.interfaces.RestaurantService;

//Autor: Tim Riebesam
//Diese Klasse implementiert das RestaurantService-Interface mit den unimplementierten Methoden

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
	
	//Übergabeparameter: Übergeben wird ein HttpServletResponse und Längen- und Breitengrad (lat, lng)
	//Funktionsweise: Wenn die beiden Variablen lat und lng null sind, werden alle Restaurants aus der Datenbank geladen. Fall keine existieren ein 404 HTTP-Error zurückgegeben.
	// Wenn nur einer der beiden lat/lng Parameter übergeben wird, wird dem response ein 400 HTTP-Error hinzugefügt.
	// Wenn beide Parameter (lat/lng) übergeben werden, ruft die Methoden getRestaurantsNearby auf.
	//Rückgabewert: Die Methode gibt eine Liste von Restaurants zurück. Der Inhalt ist von den übergebenen Parametern abhängig.
	//Errorhandling: HTTP-Error mit entsprechender Fehlermeldung und Code.
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

	//Übergabeparameter: Übergeben werden Längen- und Breitengrad (lat, lng)
	//Funktionsweise: Es wird eine Anfrage an die geoname.org API gesendet. Die Anfrage beinhaltet längen- und breitengrad. Die API sendet im Response eine Liste von Postleitzahlen, die sich im 30km Umkreis der Koordinaten befinden.
	// Über das plzRepository werden alle PLZ-Objekte aus der Datenbank mit entsprechendem plz-Attribut geladen.
	// Für jedes geladenen PLZ-Objekt wird jeder einzelne Standort geprüft, von welchem jedes Restaurant geladen wird und einer Liste hinzugefügt wird.
	//Rückgabewert: Die Liste der Restaurants wird zurückgegeben.
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

	//Funktionsweise: Lädt Restaurant über ID und restaurantRepository aus Datenbank und gibt dieses zurück. Andernfalls HTTP-Error 404
	@Override
	public Restaurant getRestaurant(UUID restaurantId, HttpServletResponse response) throws IOException {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if(restaurant == null)
			response.sendError(404, "Restaurant nicht gefunden!");
		
		return restaurant;
	}

	//Übergabeparameter: Übergeben wird eine Restaurant-ID, ein RestaurantJson-Objekt und ein HttpServletResponse
	//Funktionsweise: Diese Methode aktualisiert/ändert die Daten eine Restaurants. Hierzu wird das Restaurant über die Restaurant-Id und das restaurantRepository aus der Datenbank geladen.
	// Das RestaurantJson-Objekt beinhaltet die geänderten Daten des Restaurants. Daten die das Restaurant-Objekt selbst betreffen (name, beschriftung, email und bild) werden direkt geändert.
	// Über den geoService und die neue adresse (plz, strasse und hausnummer), werden der Längen- und Breitengrad ermittelt. Ist das Ergebnis erfolgreich und die richtigen Geodaten konnten geladen werden,
	// werden das Standort-Objekt und das PLZ-Objekt des Restaurants aktualisiert. Anschließend wird das Restaurant gespeichert.
	//Rückgabewert: Das gespeicherte Restaurant wird zurückgegeben.
	//Errorhandling: Wenn kein Restaurant gefunden wurde, wird ein 404 HTTP-Error zurückgegeben.
	// Wenn keine Geodaten zu der Adresse gefunden wurden, wird ein 400 HTTP-Error zurückgebgen.
	@Override
	public Restaurant updateRestaurant(UUID restaurantId, HttpServletResponse response, RestaurantJson restaurantJson) throws IOException {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if(restaurant == null) {
			response.sendError(404, "Restaurant nicht gefunden!");
			return null;
		}
		else {
			GeocodeXyzApiResponse locationData = geoService.getGeodataFromAddressXYZ(
					Integer.parseInt(restaurantJson.getPlz()), restaurantJson.getStrasse(), restaurantJson.getHausnummer());
			
			restaurant.setName(restaurantJson.getName());
			restaurant.setBeschreibung(restaurantJson.getBeschreibung());
			restaurant.setEmail(restaurantJson.getEmail());
			if(!restaurantJson.getBild().equals(""))
				restaurant.setBild(imageService.base64StringToByteArray(restaurantJson.getBild()));
			
			if(locationData.getError() == null && locationData.getStandard().getPostal() == Integer.parseInt(restaurantJson.getPlz())) {
				PLZ plz = restaurant.getStandort().getPlz();
				plz.setPlz(locationData.getStandard().getPostal());
				plz.setStadt(locationData.getStandard().getCity());
				plzRepository.save(plz);
				
				Standort standort = restaurant.getStandort();
				standort.setHausnummer(locationData.getStandard().getStnumber());
				standort.setStrasse(locationData.getStandard().getAddresst());
				standort.setLaengengrad(locationData.getLongt());
				standort.setBreitengrad(locationData.getLatt());
				standort.setBeschreibung(locationData.toString());
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
