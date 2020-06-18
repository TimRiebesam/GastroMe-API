package gastrome.api.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gastrome.api.entities.Feedback;
import gastrome.api.repositories.FeedbackRepository;
import gastrome.api.services.interfaces.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	

	@Override
	public Feedback addFeedback(Feedback feedback, HttpServletResponse response) throws IOException {
	   return feedbackRepository.save(feedback);
	}

}
