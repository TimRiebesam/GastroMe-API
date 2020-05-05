package gastrome.api.services;

import java.util.List;
import java.util.UUID;

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
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurant(UUID restaurantId) {
		return restaurantRepository.findById(restaurantId).orElse(null);
	}
	
}
