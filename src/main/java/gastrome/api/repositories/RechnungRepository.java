package gastrome.api.repositories;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Rechnung;
import gastrome.api.entities.Tisch;

public interface RechnungRepository extends JpaRepository<Rechnung, UUID>{

	public Rechnung findTop1ByTischByOrderByTimestamp(Tisch tisch);
	
}
