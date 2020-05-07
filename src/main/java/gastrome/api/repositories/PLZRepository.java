package gastrome.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.PLZ;

public interface PLZRepository extends JpaRepository<PLZ, UUID> {

	public List<PLZ> findAllByPlzIn(List<Integer> postalCodes);

}
