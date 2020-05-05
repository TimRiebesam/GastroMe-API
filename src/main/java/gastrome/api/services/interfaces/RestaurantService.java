package gastrome.api.services.interfaces;

import java.util.List;
import java.util.UUID;

import gastrome.api.entities.Restaurant;

public interface RestaurantService {

	public List<Restaurant> getAllRestaurants();

	public Restaurant getRestaurant(UUID restaurantId);

}
