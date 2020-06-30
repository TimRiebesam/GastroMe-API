package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Restaurant;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für Restaurant-Objekte
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

}
