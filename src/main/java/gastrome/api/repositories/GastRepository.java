package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Gast;

public interface GastRepository extends JpaRepository<Gast, UUID> {
	
}
