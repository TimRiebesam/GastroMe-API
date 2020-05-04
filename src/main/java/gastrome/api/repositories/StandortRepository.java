package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Standort;

public interface StandortRepository extends JpaRepository<Standort, UUID> {

}
