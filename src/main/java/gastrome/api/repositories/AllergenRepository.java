package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Allergen;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Allergen-Objekte
public interface AllergenRepository extends JpaRepository<Allergen, UUID> {

}
