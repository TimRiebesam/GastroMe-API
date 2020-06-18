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


@RestController
public class FeedbackController {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	FeedbackService feedbackService;
	
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
