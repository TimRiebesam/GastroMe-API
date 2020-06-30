package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.entities.GetraenkOrder;
import gastrome.api.entities.Rechnung;
import gastrome.api.services.interfaces.RechnungService;

// Autor: Tim Riebesam
// Diese Klasse stellt den Rest Controller für die Rechnung-Kommunikation bereit

@RestController
public class RechnungController {
	
	@Autowired
	RechnungService rechnungService;
	
	// Funktionsweise: Hier wird ein Patch-Request verarbeitet, der eine Speise zur Rechnung hinzufügt. 
	// Übergabeparameter: Übergeben wird die Rechnung-ID (in der URL), die Speise-ID (im Body der Anfrage) und ein HttpServletResponse (um den Rückgabewert an den Anfragenden zu manipulieren)
	// Rückgabewert: Es wird der Returnwert der aufgerufenen Methode appSpeise aus der Klasse rechnungService zurückgegeben.
	@PatchMapping(path = {"rechnung/{rechnungId}/add/speise"})
	public Rechnung addSpeise(@PathVariable UUID rechnungId, @RequestBody UUID speiseId, HttpServletResponse response) throws IOException {
		return rechnungService.addSpeise(rechnungId, speiseId, response);
	}
	
	// Siehe oben, nur diesmal mit einem Getränk
	@PatchMapping(path = {"rechnung/{rechnungId}/add/getraenk"})
	public Rechnung addGetraenk(@PathVariable UUID rechnungId, @RequestBody String getraenkId, HttpServletResponse response) throws IOException {
		return rechnungService.addGetraenk(rechnungId, UUID.fromString(getraenkId), response);
	}
	
	// Siehe oben, nur diesmal wird eine Rechnung bezahlt.
	@PatchMapping(path = {"rechnung/{rechnungId}/pay"})
	public Rechnung payRechnung(@PathVariable UUID rechnungId, HttpServletResponse response) throws IOException {
		return rechnungService.payRechnung(rechnungId, response);
	}
	
	// Siehe oben, nur diesmal wird eine Order akzeptiert.
	@PatchMapping(path = {"rechnung/acceptorder"})
	public GetraenkOrder acceptOrder(@RequestBody String getraenkOrderId, HttpServletResponse response) throws IOException {
		return rechnungService.acceptOrder(UUID.fromString(getraenkOrderId), response);
	}
	
}
