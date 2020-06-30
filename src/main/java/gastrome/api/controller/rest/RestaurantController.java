package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Restaurant;
import gastrome.api.entities.RestaurantJson;
import gastrome.api.services.interfaces.RestaurantService;

//Autor: Tim Riebesam
//Diese Klasse stellt den Rest Controller für die Restaurant-Kommunikation bereit. Die Anfragen werden an den entsprechenden Service weitergeleitet.

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;

	// Funktionsweise: Hier wird ein Get-Request verarbeitet, der eine Liste an Restaurants zurückliefert. 
	// Übergabeparameter: Übergeben wird ein HttpServletResponse (um den Rückgabewert an den Anfragenden zu manipulieren) und optional zwei Double-Variablen, die einen Längen- und Breitengrad darstellen.
	// Rückgabewert: Es wird der Returnwert der aufgerufenen Methode getAllRestaurants aus der Klasse restaurantService zurückgegeben.
	@GetMapping(path= {"restaurant/all"})
	public List<Restaurant> getAllRestaurants(HttpServletResponse response, @RequestParam(required = false) Double lat, @RequestParam(required = false) Double lng) throws IOException {
		return restaurantService.getAllRestaurants(response, lat, lng);
	}
	
	//Siehe oben, nur wird diesmal exakt ein Restaurant abgefragt dessen ID der übergebene entspricht.
	@GetMapping(path= {"restaurant/{restaurantId}"})
	public Restaurant getRestaurant(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
		return restaurantService.getRestaurant(restaurantId, response);
	}
	
	//Siehe oben, nur wird diesmal exakt ein Restaurant abgefragt dessen ID der übergebene entspricht, welches anschließend manipuliert werden soll.
	@PutMapping(path = {"restaurant/{restaurantId}"})
	public Restaurant updateRestaurant(@PathVariable UUID restaurantId, HttpServletResponse response, @RequestBody RestaurantJson restaurantJson) throws IOException {
		return restaurantService.updateRestaurant(restaurantId, response, restaurantJson);
	}
	
}
