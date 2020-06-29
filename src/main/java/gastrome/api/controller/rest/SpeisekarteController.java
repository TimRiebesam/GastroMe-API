package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Speisekarte;
import gastrome.api.services.interfaces.SpeisekarteService;

//Autor: Tim Bayer
//Diese Klasse stellt den Rest Controller für die Speisekarte-Kommunikation bereit

@RestController
public class SpeisekarteController {


		@Autowired
		SpeisekarteService speisekarteService;

		//Funktionsweise: Hier wird eine getRequest verarbeitet, die die Speisekarte anhand ihrer Id beschafft und zurückliefert
		@GetMapping(path= {"speisekarte/{speisekarteId}"})
		public Speisekarte getSpeisekarte(@PathVariable UUID speisekarteId, HttpServletResponse response) throws IOException {
			return speisekarteService.getSpeisekarte(speisekarteId, response);
		}
		
		//Funktionsweise: Hier wird eine getRequest verarbeitet, die die Speisekarte anhand der RestaurantId beschafft und zurückliefert
		@GetMapping(path= {"speisekarte/restaurant/{restaurantId}"})
		public Speisekarte getSpeisekarteByRestaurantId(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
			return speisekarteService.getSpeisekarteByRestaurantId(restaurantId, response);
		}

}
	

