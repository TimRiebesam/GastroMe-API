package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Bewertung;
import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Rezession;
import gastrome.api.repositories.BewertungRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.services.interfaces.RezessionService;

//Autor: Tim Bayer
//Diese Klasse stellt den Rest Controller für die Rezession-Kommunikation bereit

@RestController
public class RezessionController {
	
	@Autowired
	RezessionService rezessionService;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	BewertungRepository bewertungRepository;
	
	//Funktionsweise: Hier wird eine Post-Request mit den Angaben für eine externe Rezession als RequestParameter verarbeitet. Es wir ein neues Rezession Objekt erstellt und über den RezesisonService persistiert
	//Übergabeparameter: Die RestaurantId zur Identifikation des betroffenen Restaurants, die Bewertung der 5 Kategorien sowie eine Anmerkung als Rezession-Bestandteil und die Standardmäßige HttpServletResponse
	//Rückgabewert: Das erzeugte Rezession Objekt wird zur Kontrolle zurückgegeben
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
	
	@GetMapping(path= {"rezession/all/restaurant/{restaurantId}"})
	public List<Rezession> getRezessionsByRestaurantId(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
		return rezessionService.getRezessionenByRestaurantId(restaurantId, response);
	}

}
