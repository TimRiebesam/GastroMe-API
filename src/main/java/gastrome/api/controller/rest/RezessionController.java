package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Bewertung;
import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Rezession;
import gastrome.api.repositories.BewertungRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.services.interfaces.RezessionService;



@RestController
public class RezessionController {
	
	@Autowired
	RezessionService rezessionService;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	BewertungRepository bewertungRepository;
	
	//@PostMapping(path = {"rezession/add/{restaurantId}&{essen}&{atmosphaere}&{service}&{preise}&{sonderwuensche}&{anmerkung}"})
	@PostMapping(path = {"rezession/add/"})
	public Rezession addRezession(@RequestParam String restaurantId, 
			@RequestParam String essen, 
			@RequestParam String atmosphaere,
			@RequestParam String service,
			@RequestParam String preise,
			@RequestParam String sonderwuensche,
			@RequestParam String anmerkung,
			HttpServletResponse response) throws IOException {
		
		Bewertung bewertung = new Bewertung(
				Integer.parseInt(essen),
				Integer.parseInt(atmosphaere),
				Integer.parseInt(service),
				Integer.parseInt(preise),
				Integer.parseInt(sonderwuensche)
				);
		Restaurant restaurant = restaurantRepository.findById(UUID.fromString(restaurantId)).orElse(null);
		Rezession rezession = new Rezession(restaurant, anmerkung, bewertung);
		rezessionService.addRezession(rezession, response);
		bewertung.setRezession(rezession);
		bewertungRepository.save(bewertung);
		
		return rezession;
	}

}
