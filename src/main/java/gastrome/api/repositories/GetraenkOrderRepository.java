package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.GetraenkOrder;
//Autor: Tim Riebesam
//Diese Klasse implementiert das JPA Repository für GetraenkOrder-Objekte
public interface GetraenkOrderRepository extends JpaRepository<GetraenkOrder, UUID> {

}
