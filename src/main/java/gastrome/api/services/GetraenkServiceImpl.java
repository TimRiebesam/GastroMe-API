package gastrome.api.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Getraenk;
import gastrome.api.repositories.GetraenkRepository;
import gastrome.api.services.interfaces.GetraenkService;


@Service
public class GetraenkServiceImpl implements GetraenkService{
	
	@Autowired
	GetraenkRepository getraenkRepository;
	
	@Override
	public byte[] getBild(UUID getraenkId) {
		Getraenk getraenk = getraenkRepository.findById(getraenkId).orElse(null);
		if(getraenk != null && getraenk.getBild() != null)
			return getraenk.getBild();
		return null;
	}
	
}
