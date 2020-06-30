package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Speise;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Speise-Objekte
public interface SpeiseRepository extends JpaRepository<Speise, UUID> {

}
