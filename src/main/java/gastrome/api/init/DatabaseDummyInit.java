package gastrome.api.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gastrome.api.entities.Restaurant;
import gastrome.api.repositories.AllergenRepository;
import gastrome.api.repositories.BewertungRepository;
import gastrome.api.repositories.GetraenkRepository;
import gastrome.api.repositories.PLZRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.RezessionRepository;
import gastrome.api.repositories.SpeiseRepository;
import gastrome.api.repositories.SpeisekarteRepository;
import gastrome.api.repositories.StandortRepository;

@Component
public class DatabaseDummyInit {

	@Autowired
	AllergenRepository allergenRepository;
	
	@Autowired
	BewertungRepository bewertungRepository;
	
	@Autowired
	GetraenkRepository getraenkRepository;
	
	@Autowired
	PLZRepository plzRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RezessionRepository rezessionRepository;
	
	@Autowired
	SpeiseRepository speiseRepository;
	
	@Autowired
	SpeisekarteRepository speisekarteRepository;
	
	@Autowired
	StandortRepository standortRepository;
	
	@PostConstruct
	public void loadDummyDataIntoDatabase() {
		clearDatabase();
		fillDatabaseWithInitData();
	}
	
	private void clearDatabase() {
		allergenRepository.deleteAll();
		bewertungRepository.deleteAll();
		getraenkRepository.deleteAll();
		plzRepository.deleteAll();
		restaurantRepository.deleteAll();
		rezessionRepository.deleteAll();
		speiseRepository.deleteAll();
		speisekarteRepository.deleteAll();
		standortRepository.deleteAll();
	}
	
	private void fillDatabaseWithInitData() {
		Restaurant cafeSimple = new Restaurant("Café Simple");
		cafeSimple = restaurantRepository.save(cafeSimple);
	}
	
}
