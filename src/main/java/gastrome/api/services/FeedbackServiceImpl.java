package gastrome.api.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Feedback;
import gastrome.api.repositories.FeedbackRepository;
import gastrome.api.services.interfaces.FeedbackService;

//Autor: Tim Bayer
//Diese Klasse implementiert das FeedbackService-Interface mit den unimplementierten Methoden

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	//Funktionsweise: Es wird ein Feedback übergeben, dieses wird über das Repository persistiert und zu Kontrollzwecken zurückgeliefert
	@Override
	public Feedback addFeedback(Feedback feedback, HttpServletResponse response) throws IOException {
	   return feedbackRepository.save(feedback);
	}

}
