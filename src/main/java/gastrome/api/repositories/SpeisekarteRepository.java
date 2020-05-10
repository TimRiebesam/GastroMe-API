package gastrome.api.repositories;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Speisekarte;

public interface SpeisekarteRepository extends JpaRepository<Speisekarte, UUID> {
	
	public Speisekarte getSpeisekarteByRestaurantId(UUID restaurantID);

}
