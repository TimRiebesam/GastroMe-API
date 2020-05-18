package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Speisekarte;
import gastrome.api.entities.Tisch;

public interface TischRepository extends JpaRepository<Tisch, UUID> {
	
	public List<Tisch> findAllByRestaurantId(UUID restaurantID);
	
	//public void addGast(UUID gastId, UUID TischId, HttpServletResponse response);
	
	//public void clearGaeste(UUID TischId, HttpServletResponse response);

}
