package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

}
