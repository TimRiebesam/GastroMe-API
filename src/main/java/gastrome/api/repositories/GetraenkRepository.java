package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Getraenk;

public interface GetraenkRepository extends JpaRepository<Getraenk, UUID> {

}
