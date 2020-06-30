package gastrome.api.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Speise;
import gastrome.api.repositories.SpeiseRepository;
import gastrome.api.services.interfaces.SpeiseService;

//Autor: Tim Riebesam
//Diese Klasse implementiert das SpeiseService-Interface mit den unimplementierten Methoden

@Service
public class SpeiseServiceImpl implements SpeiseService{
	
	@Autowired
	SpeiseRepository speiseRepository;
	
	//Funktionsweise: Es wird über das speiseRepository die Speise zur entspechenden übergebenen UUID gesucht und wenn es gefunden wurde, wird das Bild der Speise zurückgegeben.
	@Override
	public byte[] getBild(UUID speiseId) {
		Speise speise = speiseRepository.findById(speiseId).orElse(null);
		if(speise != null && speise.getBild() != null)
			return speise.getBild();
		return null;
	}
	
}
