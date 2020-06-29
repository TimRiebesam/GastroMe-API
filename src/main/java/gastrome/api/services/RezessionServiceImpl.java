package gastrome.api.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Rezession;
import gastrome.api.repositories.RezessionRepository;
import gastrome.api.services.interfaces.RezessionService;
//Autor: Tim Bayer
//Diese Klasse implementiert das RezessionService-Interface mit den unimplementierten Methoden
@Service
public class RezessionServiceImpl implements RezessionService {

	@Autowired
	RezessionRepository rezessionRepository;
	

	//Funktionsweise: Es wird eine Rezession übergeben, dieses wird über das Repository persistiert und zu Kontrollzwecken zurückgeliefert
	@Override
	public Rezession addRezession(Rezession rezession, HttpServletResponse response) throws IOException {
	   return rezessionRepository.save(rezession);
	}

	//Funktionsweise: Es werden alle Rezessionen eines Restaurants anhand der RestaurantId zurückgeliefert
	@Override
	public List<Rezession> getRezessionenByRestaurantId(UUID restaurantID, HttpServletResponse response)
			throws IOException {
		List<Rezession> rezessionen = rezessionRepository.getRezessionsByRestaurantId(restaurantID);
		if(rezessionen == null)
			response.sendError(404, "Restaurant nicht gefunden!");
		
		return rezessionen;
	}

}
