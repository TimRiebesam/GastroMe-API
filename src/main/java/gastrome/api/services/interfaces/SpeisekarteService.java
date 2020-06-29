package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Speisekarte;
//Autor: Tim Bayer
//Dieses Interface definiert die erfolderlichen Methoden einer SpeisekarteService Implementierung

public interface SpeisekarteService {

	//Funktionsweise: abstrakte Methode um eine Speisekarte zu beschaffen
	public Speisekarte getSpeisekarte(UUID speisekarteId, HttpServletResponse response) throws IOException;
	
	//Funktionsweise: abstrakte Methode um die Speisekarte eines Restaurants zu beschaffen
	public Speisekarte getSpeisekarteByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException;
	
}
