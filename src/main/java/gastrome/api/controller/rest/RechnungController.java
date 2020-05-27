package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.Rechnung;
import gastrome.api.services.interfaces.RechnungService;

@RestController
public class RechnungController {
	
	@Autowired
	RechnungService rechnungService;
	

	@PatchMapping(path = {"rechnung/{rechnungId}/add/speise"})
	public Rechnung addSpeise(@PathVariable UUID rechnungId, @RequestBody UUID speiseId, HttpServletResponse response) throws IOException {
		return rechnungService.addSpeise(rechnungId, speiseId, response);
	}
	
	@PatchMapping(path = {"rechnung/{rechnungId}/add/getraenk"})
	public Rechnung addGetraenk(@PathVariable UUID rechnungId, @RequestBody UUID getraenkId, HttpServletResponse response) throws IOException {
		return rechnungService.addGetraenk(rechnungId, getraenkId, response);
	}
	
}
