package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.GetraenkOrder;

public interface GetraenkOrderRepository extends JpaRepository<GetraenkOrder, UUID> {

}
