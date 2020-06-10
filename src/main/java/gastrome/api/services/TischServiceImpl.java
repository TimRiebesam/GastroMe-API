package gastrome.api.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Gast;
import gastrome.api.entities.Rechnung;
import gastrome.api.entities.Tisch;
import gastrome.api.repositories.GastRepository;
import gastrome.api.repositories.RechnungRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.TischRepository;
import gastrome.api.services.interfaces.ImageService;
import gastrome.api.services.interfaces.QrCodeService;
import gastrome.api.services.interfaces.TischService;

@Service
public class TischServiceImpl implements TischService{

	@Autowired
	TischRepository tischRepository;
	
	@Autowired
	GastRepository gastRepository;
	
	@Autowired
	RechnungRepository rechnungRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	QrCodeService qrCodeService;

	@Override
	public void addGast(Gast gast, UUID tischId, HttpServletResponse response) throws IOException {
		try{
			Tisch tisch = tischRepository.findTischById(tischId);
			gast.setTisch(tisch);
			tisch.addGast(gast);
			gastRepository.save(gast);
			tischRepository.save(tisch);
		} catch (Exception e) {
			response.sendError(400, e.toString());
		}
	}

	@Override
	public void clearGaeste(UUID tischId, HttpServletResponse response) throws IOException {
		try{
			Tisch tisch = tischRepository.findTischById(tischId);
			tisch.clearGaeste();
			tischRepository.save(tisch);
		}catch (Exception e) {
			response.sendError(400, e.toString());
		}
	}

	@Override
	public List<Tisch> getTischeByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException {
		List<Tisch> liste = tischRepository.findAllByRestaurantId(restaurantID);
		if(liste == null)
			response.sendError(404, "Restaurant nicht gefunden!");
		
		return liste;
		
	}

	@Override
	public void addQrCodeToResponse(UUID tischId, HttpServletResponse response, boolean sw) throws Exception {
		imageService.addImageToResponse(qrCodeService.generate("GastroMe-Wasserzeichen\n" + tischRepository.findById(tischId).get().getRestaurant().getId() + "\n" + tischId, sw), response);		
	}

	@Override
	public Rechnung getLatestRechnungForTisch(UUID tischId) {
		Tisch tisch = tischRepository.findById(tischId).orElse(null);
		if(tisch != null) {
			Rechnung rechnung = rechnungRepository.findTop1ByTischOrderByTimestampDesc(tisch).orElse(null);
			if(rechnung == null || rechnung.isBillPayed()) {
				rechnung = new Rechnung();
				rechnung.setTisch(tischRepository.findTischById(tischId));
				rechnung.setTimestamp(new Date());
				return rechnungRepository.save(rechnung);
			} else
				return rechnung;
		}
		return null;
	}
	
	
	
}
