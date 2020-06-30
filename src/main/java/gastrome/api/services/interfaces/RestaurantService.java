package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Restaurant;
import gastrome.api.entities.RestaurantJson;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer RestaurantService Implementierung

public interface RestaurantService {

	//Funktionsweise: abstrakte Methode um eine Liste von Restaurants zu erhalten
	public List<Restaurant> getAllRestaurants(HttpServletResponse response, Double lat, Double lng) throws IOException;

	//Funktionsweise: abstrakte Methode um ein Restaurant zu erhalten
	public Restaurant getRestaurant(UUID restaurantId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um die Daten eines Restaurant zu ändern
	public Restaurant updateRestaurant(UUID restaurantId, HttpServletResponse response, RestaurantJson restaurantJson) throws IOException;

}
