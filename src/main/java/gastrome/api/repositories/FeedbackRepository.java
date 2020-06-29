package gastrome.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import gastrome.api.entities.Feedback;
//Autor: Tim Bayer
//Diese Klasse implementiert das JPA Repository für Feedback-Objekte
public interface FeedbackRepository extends JpaRepository<Feedback, UUID>  {

}
