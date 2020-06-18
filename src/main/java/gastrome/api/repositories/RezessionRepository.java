package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Rezession;

public interface RezessionRepository extends JpaRepository<Rezession, UUID> {
	
	public List<Rezession> getRezessionsByRestaurantId(UUID restaurantId);

}
