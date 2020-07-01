package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Bewertung;
//Autor: Tim Bayer, Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Bewertungs-Objekte
public interface BewertungRepository extends JpaRepository<Bewertung, UUID> {

}
