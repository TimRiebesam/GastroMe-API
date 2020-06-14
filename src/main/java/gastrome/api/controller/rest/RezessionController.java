package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Bewertung;
import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Rezession;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.services.interfaces.RezessionService;



@RestController
public class RezessionController {
	
	@Autowired
	RezessionService rezessionService;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@PatchMapping(path = {"rezession/add/"})
	public Rezession addRezession(@RequestBody Rezession rezession, HttpServletResponse response) throws IOException {
		//Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		//rezession = new Rezession(restaurant, "War gut", new Bewertung(4,3,5,5,3));
		return rezessionService.addRezession(rezession, response);
	}

}
