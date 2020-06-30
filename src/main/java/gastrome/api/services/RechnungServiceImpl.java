package gastrome.api.services;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.GetraenkOrder;
import gastrome.api.entities.Rechnung;
import gastrome.api.repositories.GetraenkOrderRepository;
import gastrome.api.repositories.GetraenkRepository;
import gastrome.api.repositories.RechnungRepository;
import gastrome.api.repositories.SpeiseRepository;
import gastrome.api.services.interfaces.RechnungService;

//Autor: Tim Riebesam
//Diese Klasse implementiert das RechnungService-Interface mit den unimplementierten Methoden

@Service
public class RechnungServiceImpl implements RechnungService{

	@Autowired
	SpeiseRepository speiseRepository;
	
	@Autowired
	GetraenkRepository getraenkRepository;
	
	@Autowired
	RechnungRepository rechnungRepository;
	
	@Autowired
	GetraenkOrderRepository getraenkOrderRepository;
	
	//Übergabeparameter: Übergeben werden eine Rechnung-Id, eine Speise-ID und ein HttpServletResponse
	//Funktionsweise: Diese Methode holt über das rechnungRepository eine Rechnung. der Rechnung wird anschließend über das speiseRepository und die Speise-ID eine Speise hinzugefügt. anschließend wird die Rechnung gespeichert.
	//Rückgabewert: Rückgabewert ist die gespeicherte Rechnung
	//Errorhandling: Bei einem Fehler wird dem Response ein HTTP-Error 400 hinzugefügt.
	@Override
	public Rechnung addSpeise(UUID rechnungId, UUID speiseId, HttpServletResponse response) throws IOException {
		try {
			Rechnung rechnung = rechnungRepository.findById(rechnungId).orElse(null);
			rechnung.addSpeise(speiseRepository.findById(speiseId).orElse(null));
			return rechnungRepository.save(rechnung);
		} catch (Exception e) {
			response.sendError(400, e.toString());
			return null;
		}
	}
	
	//Siehe oben nur mit Getränk statt Speise
	@Override
	public Rechnung addGetraenk(UUID rechnungId, UUID getraenkId, HttpServletResponse response) throws IOException {
		try {
			Rechnung rechnung = rechnungRepository.findById(rechnungId).orElse(null);
			GetraenkOrder getraenkOrder = getraenkOrderRepository.save(new GetraenkOrder(rechnung, getraenkRepository.findById(getraenkId).orElse(null)));
			return getraenkOrder.getRechnung();
		} catch (Exception e) {
			response.sendError(400, e.toString());
			return null;
		}
	}

	//Siehe oben, nur wird eine Rechnung auf bezahlt gesetzt
	@Override
	public Rechnung payRechnung(UUID rechnungId, HttpServletResponse response) throws IOException {
		try {
			Rechnung rechnung = rechnungRepository.findById(rechnungId).orElse(null);
			rechnung.setBillPayed(true);
			return rechnungRepository.save(rechnung);
		} catch (Exception e) {
			response.sendError(400, e.toString());
			return null;
		}
	}

	//Siehe oben, nur wird eine Bestellung auf ausgeliefert gesetzt
	@Override
	public GetraenkOrder acceptOrder(UUID getraenkOrderId, HttpServletResponse response) throws IOException {
		try {
			GetraenkOrder getraenkOrder = getraenkOrderRepository.findById(getraenkOrderId).orElse(null);
			getraenkOrder.setAusgeliefert(true);
			return getraenkOrderRepository.save(getraenkOrder);
		} catch (Exception e) {
			response.sendError(400, e.toString());
			return null;
		}
	}

	
	
}
