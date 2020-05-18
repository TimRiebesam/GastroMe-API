package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Speisekarte;
import gastrome.api.entities.Tisch;

public interface TischService {

	public List<Tisch> getTischeByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException;
	
	public void addGast(UUID gastId, UUID tischId, HttpServletResponse response) throws IOException;
	
	public void clearGaeste(UUID tischId, HttpServletResponse response) throws IOException;
}
