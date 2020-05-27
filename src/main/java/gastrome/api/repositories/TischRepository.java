package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Tisch;

public interface TischRepository extends JpaRepository<Tisch, UUID> {
	
	public List<Tisch> findAllByRestaurantId(UUID restaurantID);

	public Tisch findTischById(UUID tischId);
	
	//public void insertGastintoGaeste(UUID gastId, UUID TischId, HttpServletResponse response);
	
	//public void clearGaeste(UUID TischId, HttpServletResponse response);

}
