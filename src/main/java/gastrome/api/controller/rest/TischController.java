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

//Autor: Tim Bayer, Tim Riebesam
//Diese Klasse stellt den Rest Controller für die Tisch-Kommunikation bereit

@RestController
public class TischController {

	@Autowired
	TischService tischService;
	
	//Funktionsweise: Hier wird eine Patch-Request verarbeitet, es wird ein Gast der Gästeliste des Tisches hinzugefügt
	//Übergabeparameter: Es wird die TischId und die Standardmäßige HttpServletResponse übergeben
	//Rückgabewert: Es wird eine Erfolgsmeldung zurückgegeben
	@PatchMapping(path= {"tisch/gaesteliste/add/{tischId}"})
	public String addGast(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.addGast(new Gast(), tischId, response);
		return "Gast hinzugefuegt";
	}
	
	//Funktionsweise: Hier wird eine Patch-Request verarbeitet, es wird die Gästeliste des Tisches geleert
	//Übergabeparameter: Es wird die TischId und die Standardmäßige HttpServletResponse übergeben
	//Rückgabewert: Es wird eine Erfolgsmeldung zurückgegeben
	@PatchMapping(path= {"tisch/gaesteliste/clear/{tischId}"})
	public String clearGaeste(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.clearGaeste(tischId, response);
		return "Gaesteliste geleert";
	}

	//Funktionsweise: Über diese GetRequest werden alle Tische eines Restaurants anhand der RestaurantId beschafft und zurückgeliefert
	@GetMapping(path= {"/tisch/restaurant/{restaurantId}"})
	public List<Tisch> getTischeByRestaurantId(@PathVariable UUID restaurantId, HttpServletResponse response) throws IOException {
		return tischService.getTischeByRestaurantId(restaurantId, response);
	}
	
	//Funktionsweise: Über diese DeleteRequest wird ein Tisch anhand der TischId gelöscht
	@DeleteMapping(path = {"tisch/{tischId}"})
	public void deleteTisch(@PathVariable UUID tischId, HttpServletResponse response) throws IOException {
		tischService.deleteTisch(tischId, response);
	}
	
	//Funktionsweise: Über diese PatchRequest wird die Beschreibtung eines Tisches aktualisiert
	@PatchMapping(path = {"tisch/{tischId}"})
	public Tisch updateTisch(@PathVariable UUID tischId, HttpServletResponse response, @RequestBody String beschreibung) throws IOException {
		return tischService.updateTisch(tischId, response, beschreibung);
	}
	
	//Funktionsweise: Über diese PostRequest wird ein Tisch erstellt und anhand der RestaurantId einem Restaurant hinzugefügt
	@PostMapping(path= {"/tisch/add/restaurant/{restaurantId}"})
	public Tisch addTisch (@PathVariable UUID restaurantId, HttpServletResponse response, @RequestParam(required = false) String beschreibung) throws IOException {
		return tischService.addTisch(restaurantId, response, beschreibung);
	}
	
	//Funktionsweise: Über diese GetRequest wird anhand der TischId ein Qr-Code für den Tisch erzeugt 
	@GetMapping(path= {"tisch/{tischId}/qr"})
	public void getQrCode(@PathVariable UUID tischId, HttpServletResponse response, @RequestParam(required = false) boolean sw) throws Exception {
		tischService.addQrCodeToResponse(tischId, response, sw);
	}

	//Funktionsweise: Über diese GetRequest wird anhand der TischId die Rechnung des Tisches geliefert
	@GetMapping(path = {"tisch/{tischId}/currentRechnung"})
	public Rechnung getLatestRechnungForTisch(@PathVariable UUID tischId) {
		return tischService.getLatestRechnungForTisch(tischId);
	}
	
	//Funktionsweise: Über diese PatchRequest wird anhand der TischId der Kellner zu einem Tisch gerufen
	@PatchMapping(path = {"tisch/{tischId}/kellner"})
	public String kellnerCalled(@PathVariable UUID tischId,HttpServletResponse response) throws IOException {
		tischService.callKellner(tischId, response);
		return "Kellner gerufen";
	}
	
	//Funktionsweise: Über diese PatchRequest wird anhand der TischId der Kellner-gerufen Status als beendigt erklärt
	@PatchMapping(path = {"tisch/{tischId}/kellner/done"})
	public Tisch kellnerCalledDone(@PathVariable UUID tischId,HttpServletResponse response) throws IOException {
		return tischService.kellnerCalledDone(tischId, response);
	}
	
}
