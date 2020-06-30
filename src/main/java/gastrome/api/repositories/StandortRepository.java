package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Standort;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Standort-Objekte
public interface StandortRepository extends JpaRepository<Standort, UUID> {

}
