package gastrome.api.services.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Rezession;


public interface RezessionService {

	public Rezession addRezession(Rezession rezession, HttpServletResponse response) throws IOException;
	
}
