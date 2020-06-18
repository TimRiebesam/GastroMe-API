package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID>  {

}
