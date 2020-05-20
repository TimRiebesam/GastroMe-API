package gastrome.api.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Gast;
import gastrome.api.entities.Tisch;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.SpeisekarteRepository;
import gastrome.api.repositories.TischRepository;
import gastrome.api.services.interfaces.TischService;

@Service
public class TischServiceImpl implements TischService{

	@Autowired
	TischRepository tischRepository;

	@Override
	public void addGast(Gast gast, UUID tischId, HttpServletResponse response) throws IOException {
		try{
			Tisch tisch = tischRepository.findTischById(tischId);
			System.out.print("tisch gefunden");
			tisch.addGast(gast);
			System.out.print("Gast hinzugefügt");
			tischRepository.save(tisch);
			System.out.print("gespeichert");
		} catch (Exception e) {
			response.sendError(400, e.toString());
		}
	}

	@Override
	public void clearGaeste(UUID tischId, HttpServletResponse response) throws IOException {
		try{
			//tischRepository.clearGaeste(tischId, response);
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
	
	
	
}
