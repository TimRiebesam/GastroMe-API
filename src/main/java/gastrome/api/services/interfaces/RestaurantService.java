package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Restaurant;
import gastrome.api.entities.RestaurantJson;

public interface RestaurantService {

	public List<Restaurant> getAllRestaurants(HttpServletResponse response, Double lat, Double lng) throws IOException;

	public Restaurant getRestaurant(UUID restaurantId, HttpServletResponse response) throws IOException;

	public Restaurant updateRestaurant(UUID restaurantId, HttpServletResponse response, RestaurantJson restaurantJson) throws IOException;

}
