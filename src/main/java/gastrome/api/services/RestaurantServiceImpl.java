package gastrome.api.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Restaurant;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.services.interfaces.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> getAllRestaurants(HttpServletResponse response) throws IOException {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		if(!restaurants.isEmpty())
			return restaurants;
		
		response.sendError(404, "Keine Restaurants vorhanden!");
		return null;
	}

	@Override
	public Restaurant getRestaurant(UUID restaurantId, HttpServletResponse response) throws IOException {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if(restaurant == null)
			response.sendError(404, "Restaurant nicht gefunden!");
		
		return restaurant;
	}
	
}
