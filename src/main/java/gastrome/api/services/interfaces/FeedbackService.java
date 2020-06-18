package gastrome.api.services.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Feedback;

public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback, HttpServletResponse response) throws IOException;

}
