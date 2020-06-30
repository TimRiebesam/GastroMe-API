package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Getraenk;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Getraenk-Objekte
public interface GetraenkRepository extends JpaRepository<Getraenk, UUID> {

}
