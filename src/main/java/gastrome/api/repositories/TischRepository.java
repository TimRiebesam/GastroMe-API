package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Tisch;

//Autor: Tim Bayer, Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Tisch-Objekte
public interface TischRepository extends JpaRepository<Tisch, UUID> {
	
	//Funktionsweise: Es werden alle Tische eines Restaurants anhand der RestaurantId beschafft und geliefert
	public List<Tisch> findAllByRestaurantId(UUID restaurantID);

	//Funtkiionsweise: Es wird ein Tisch anhand der TischId beschafft und geliefert
	public Tisch findTischById(UUID tischId);
	

}
