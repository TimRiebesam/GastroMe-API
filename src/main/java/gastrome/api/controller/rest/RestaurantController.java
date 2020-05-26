package gastrome.api.controller.rest;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Restaurant;
import gastrome.api.services.interfaces.RestaurantService;


@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;

	@GetMapping(path= {"restaurant/all"})
	public List<Restaurant> getAllRestaurants(HttpServletResponse response, @RequestParam(required = false) Double lat, @RequestParam(required = false) Double lng) throws IOException {
		return restaurantService.getAllRestaurants(response, lat, lng);
	}
	
	@GetMapping(path= {"restaurant/{restaurantId}"})
	public Restaurant getRestaurant(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
		return restaurantService.getRestaurant(restaurantId, response);
	}
	
}
