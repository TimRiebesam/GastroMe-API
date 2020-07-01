package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Gast;
//Autor: Tim Bayer, Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Gast-Objekte
public interface GastRepository extends JpaRepository<Gast, UUID> {
	
}
