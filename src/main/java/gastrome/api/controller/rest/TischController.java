package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Tisch;
import gastrome.api.services.interfaces.TischService;

@RestController
public class TischController {

	@Autowired
	TischService tischService;
	
	@PatchMapping(path= {"tisch/gaesteliste/add/{gastID}/{tischId}"})
	public void addGast(@PathVariable UUID gastId, @PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.addGast(gastId, tischId, response);
	}
	
	@PatchMapping(path= {"tisch/gaesteliste/clear/{tischId}"})
	public void addGast(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.clearGaeste(tischId, response);
	}

	@GetMapping(path= {"/tisch/restaurant/{restaurantId}"})
	public List<Tisch> getTischeByRestaurantId(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
		return tischService.getTischeByRestaurantId(restaurantId, response);
	}

}
