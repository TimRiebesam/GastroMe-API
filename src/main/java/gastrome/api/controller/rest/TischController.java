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

import gastrome.api.entities.Gast;
import gastrome.api.entities.Tisch;
import gastrome.api.services.interfaces.ImageService;
import gastrome.api.services.interfaces.QrCodeService;
import gastrome.api.services.interfaces.TischService;

@RestController
public class TischController {

	@Autowired
	TischService tischService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	QrCodeService qrCodeService;
	
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
	
	@GetMapping(path= {"tisch/{tischId}/qr"})
	public void getQrCode(@PathVariable UUID tischId, HttpServletResponse response) throws Exception {
		imageService.addImageToResponse(qrCodeService.generate("GastroMe-Wasserzeichen\n" + tischId), response);
	}

}
