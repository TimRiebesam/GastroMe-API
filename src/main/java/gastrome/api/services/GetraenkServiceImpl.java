package gastrome.api.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Getraenk;
import gastrome.api.repositories.GetraenkRepository;
import gastrome.api.services.interfaces.GetraenkService;

//Autor: Tim Riebesam
//Diese Klasse implementiert das GetraenkService-Interface mit den unimplementierten Methoden

@Service
public class GetraenkServiceImpl implements GetraenkService{
	
	@Autowired
	GetraenkRepository getraenkRepository;
	
	//Funktionsweise: Es wird über das getraenkRepository das Getränk zur entspechenden übergebenen UUID gesucht und wenn es gefunden wurde, wird das Bild des Getränks zurückgegeben.
	@Override
	public byte[] getBild(UUID getraenkId) {
		Getraenk getraenk = getraenkRepository.findById(getraenkId).orElse(null);
		if(getraenk != null && getraenk.getBild() != null)
			return getraenk.getBild();
		return null;
	}
	
}
