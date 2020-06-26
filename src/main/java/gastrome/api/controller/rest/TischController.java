package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Gast;
import gastrome.api.entities.Rechnung;
import gastrome.api.entities.Tisch;
import gastrome.api.services.interfaces.TischService;

@RestController
public class TischController {

	@Autowired
	TischService tischService;
	
	@PatchMapping(path= {"tisch/gaesteliste/add/{tischId}"})
	public String addGast(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.addGast(new Gast(), tischId, response);
		return "Gast hinzugefuegt";
	}
	
	@PatchMapping(path= {"tisch/gaesteliste/clear/{tischId}"})
	public String clearGaeste(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.clearGaeste(tischId, response);
		return "Gaesteliste geleert";
	}

	@GetMapping(path= {"/tisch/restaurant/{restaurantId}"})
	public List<Tisch> getTischeByRestaurantId(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
		return tischService.getTischeByRestaurantId(restaurantId, response);
	}
	
	@DeleteMapping(path = {"tisch/{tischId}"})
	public void deleteTisch(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.deleteTisch(tischId, response);
	}
	
	@PatchMapping(path = {"tisch/{tischId}"})
	public Tisch updateTisch(@PathVariable UUID tischId, HttpServletResponse response, @RequestBody String beschreibung) throws IOException {
		return tischService.updateTisch(tischId, response, beschreibung);
	}
	
	@PostMapping(path= {"/tisch/add/restaurant/{restaurantId}"})
	public Tisch addTisch (@PathVariable UUID restaurantId, HttpServletResponse response, @RequestParam(required = false) String beschreibung) throws IOException {
		return tischService.addTisch(restaurantId, response, beschreibung);
	}
	
	@GetMapping(path= {"tisch/{tischId}/qr"})
	public void getQrCode(@PathVariable UUID tischId, HttpServletResponse response, @RequestParam(required = false) boolean sw) throws Exception {
		tischService.addQrCodeToResponse(tischId, response, sw);
	}

	@GetMapping(path = {"tisch/{tischId}/currentRechnung"})
	public Rechnung getLatestRechnungForTisch(@PathVariable UUID tischId) {
		return tischService.getLatestRechnungForTisch(tischId);
	}
	
	@PatchMapping(path = {"tisch/{tischId}/kellner"})
	public String kellnerCalled(@PathVariable UUID tischId,HttpServletResponse response) throws IOException {
		tischService.callKellner(tischId, response);
		return "Kellner gerufen";
	}
	
	@PatchMapping(path = {"tisch/{tischId}/kellner/done"})
	public Tisch kellnerCalledDone(@PathVariable UUID tischId,HttpServletResponse response) throws IOException {
		return tischService.kellnerCalledDone(tischId, response);
	}
	
}
