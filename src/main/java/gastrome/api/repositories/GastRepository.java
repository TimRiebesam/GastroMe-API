package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Gast;
import gastrome.api.entities.Tisch;

public interface GastRepository extends JpaRepository<Gast, UUID> {
	
}
