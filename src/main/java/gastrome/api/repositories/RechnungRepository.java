package gastrome.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Rechnung;
import gastrome.api.entities.Tisch;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Rechnung-Objekte
public interface RechnungRepository extends JpaRepository<Rechnung, UUID>{

	//Funktionsweise: Findet die aktuellste Rechnung zu einem übergebenen Tisch
	public Optional<Rechnung> findTop1ByTischOrderByTimestampDesc(Tisch tisch);
	
}
