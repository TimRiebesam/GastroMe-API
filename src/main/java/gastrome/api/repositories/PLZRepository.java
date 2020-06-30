package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.PLZ;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für PLZ-Objekte
public interface PLZRepository extends JpaRepository<PLZ, UUID> {

	//Funktionsweise: Findet alle PLZs deren plz-Attribut einem der übergenen Liste entspricht.
	public List<PLZ> findAllByPlzIn(List<Integer> postalCodes);

}
