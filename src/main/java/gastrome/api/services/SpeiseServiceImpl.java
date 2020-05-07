package gastrome.api.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Speise;
import gastrome.api.repositories.SpeiseRepository;
import gastrome.api.services.interfaces.SpeiseService;


@Service
public class SpeiseServiceImpl implements SpeiseService{
	
	@Autowired
	SpeiseRepository speiseRepository;
	
	@Override
	public byte[] getBild(UUID speiseId) {
		Speise speise = speiseRepository.findById(speiseId).orElse(null);
		if(speise != null && speise.getBild() != null)
			return speise.getBild();
		return null;
	}
	
}
