package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Speisekarte;
//Autor: Tim Bayer
//Diese Klasse implementiert das JPA Repository für Speisekarte-Objekte
public interface SpeisekarteRepository extends JpaRepository<Speisekarte, UUID> {
	
	//Funktionsweise: Diese Methode liefert die Speisekarte anhand der RestaurantId
	public Speisekarte getSpeisekarteByRestaurantId(UUID restaurantID);

}
