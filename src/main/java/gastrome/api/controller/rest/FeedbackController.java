package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Feedback;
import gastrome.api.entities.Restaurant;
import gastrome.api.repositories.FeedbackRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.services.interfaces.FeedbackService;

//Autor: Tim Bayer
//Diese Klasse stellt den Rest Controller für die Feedback-Kommunikation bereit

@RestController
public class FeedbackController {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	FeedbackService feedbackService;
	
	
	//Funktionsweise: Hier wird eine Post-Request mit den Angaben für das Feedback als RequestParameter verarbeitet. Es wir ein neues Feedback Objekt erstellt und über den FeedbackService persistiert
	//Übergabeparameter: Die RestaurantId zur Identifikation des betroffenen Restaurants, Kategorie und Anmerkung als Feedback-Bestandteil und die Standardmäßige HttpServletResponse
	//Rückgabewert: Das erzeugte Feedback Objekt wird zur Kontrolle zurückgegeben
	@PostMapping(path = {"feedback/add/"})
	public Feedback addFeedback(@RequestParam String restaurantId, 
			@RequestParam String kategorie, 
			@RequestParam String anmerkung,
			HttpServletResponse response) throws IOException {
		
		Restaurant restaurant = restaurantRepository.findById(UUID.fromString(restaurantId)).orElse(null);
		Feedback feedback = new Feedback(kategorie, anmerkung, restaurant);
		
		feedbackService.addFeedback(feedback, response);
		
		return feedback;
	}

}
