package gastrome.api.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Rezession;
import gastrome.api.repositories.RezessionRepository;
import gastrome.api.services.interfaces.RezessionService;

@Service
public class RezessionServiceImpl implements RezessionService {

	@Autowired
	RezessionRepository rezessionRepository;
	

	@Override
	public Rezession addRezession(Rezession rezession, HttpServletResponse response) throws IOException {
	   return rezessionRepository.save(rezession);
	}

}
