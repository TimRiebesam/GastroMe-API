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

	@Override
	public Rechnung acceptOrder(UUID getraenkOrderId, HttpServletResponse response) throws IOException {
		try {
			GetraenkOrder getraenkOrder = getraenkOrderRepository.findById(getraenkOrderId).orElse(null);
			getraenkOrder.setAusgeliefert(true);
			getraenkOrder = getraenkOrderRepository.save(getraenkOrder);
			return getraenkOrder.getRechnung();
		} catch (Exception e) {
			response.sendError(400, e.toString());
			return null;
		}
	}

	
	
}
