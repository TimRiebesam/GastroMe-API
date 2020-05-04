package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Speise;

public interface SpeiseRepository extends JpaRepository<Speise, UUID> {

}
