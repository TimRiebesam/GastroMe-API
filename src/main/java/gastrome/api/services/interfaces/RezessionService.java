package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import gastrome.api.entities.Rezession;



public interface RezessionService {

	public Rezession addRezession(Rezession rezession, HttpServletResponse response) throws IOException;
	
	public List<Rezession> getRezessionenByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException;
	
	
}
