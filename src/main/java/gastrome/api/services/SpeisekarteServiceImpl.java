package gastrome.api.services;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Speisekarte;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.SpeisekarteRepository;
import gastrome.api.services.interfaces.SpeisekarteService;

@Service
public class SpeisekarteServiceImpl implements SpeisekarteService{
	
	@Autowired
	SpeisekarteRepository speisekarteRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Override
	public Speisekarte getSpeisekarte(UUID speisekarteId, HttpServletResponse response) throws IOException{
		Speisekarte speisekarte = speisekarteRepository.findById(speisekarteId).orElse(null);
		if(speisekarte == null)
			response.sendError(404, "Speisekarte nicht gefunden!");
		
		return speisekarte;
	}

	@Override
	public Speisekarte getSpeisekarteByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException {
		Speisekarte speisekarte = speisekarteRepository.getSpeisekarteByRestaurantId(restaurantID);
		if(speisekarte == null)
			response.sendError(404, "Restaurant nicht gefunden!");
		
		return speisekarte;
	}
	
}
