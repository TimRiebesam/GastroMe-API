package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Allergen;

public interface AllergenRepository extends JpaRepository<Allergen, UUID> {

}
