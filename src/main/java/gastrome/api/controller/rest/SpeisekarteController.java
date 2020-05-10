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


@RestController
public class SpeisekarteController {


		@Autowired
		SpeisekarteService speisekarteService;


		@GetMapping(path= {"speisekarte/{speisekarteId}"})
		public Speisekarte getSpeisekarte(@PathVariable UUID speisekarteId, HttpServletResponse response) throws IOException {
			return speisekarteService.getSpeisekarte(speisekarteId, response);
		}
		@GetMapping(path= {"speisekarteByRestaurantId/{restaurantId}"})
		public Speisekarte getSpeisekarteByRestaurantId(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
			return speisekarteService.getSpeisekarteByRestaurantId(restaurantId, response);
		}

}
	

