package gastrome.api.controller.rest;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Restaurant;
import gastrome.api.services.interfaces.RestaurantService;


@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;

	@GetMapping(path= {"restaurant/all"})
	public List<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	@GetMapping(path= {"restaurant/{restaurantId}"})
	public Restaurant getRestaurant(@PathVariable UUID restaurantId) {
		return restaurantService.getRestaurant(restaurantId);
	}
	
}
