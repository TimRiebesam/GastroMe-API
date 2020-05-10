package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Speisekarte;

public interface SpeisekarteService {

	
	public Speisekarte getSpeisekarte(UUID speisekarteId, HttpServletResponse response) throws IOException;
	
	public Speisekarte getSpeisekarteByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException;
	
}
