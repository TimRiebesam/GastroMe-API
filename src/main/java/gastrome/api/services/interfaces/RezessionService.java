package gastrome.api.services.interfaces;
//Autor: Tim Bayer
//Dieses Interface definiert die erfolderlichen Methoden einer RezessionService Implementierung

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import gastrome.api.entities.Rezession;



public interface RezessionService {

	//Funktionsweise: abstrakte Methode um eine Rezession zu persistieren
	public Rezession addRezession(Rezession rezession, HttpServletResponse response) throws IOException;
	
	//Funktionsweise: abstrakte Methode um die Rezessionen eines Restaurant zu beschaffen
	public List<Rezession> getRezessionenByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException;
	
	
}
