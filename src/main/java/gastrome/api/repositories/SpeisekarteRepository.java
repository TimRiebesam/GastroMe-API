package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Speisekarte;

public interface SpeisekarteRepository extends JpaRepository<Speisekarte, UUID> {

}
