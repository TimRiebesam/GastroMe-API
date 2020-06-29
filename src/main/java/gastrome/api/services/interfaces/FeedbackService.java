package gastrome.api.services.interfaces;
//Autor: Tim Bayer
//Dieses Interface definiert die erfolderlichen Methoden einer FeedbackService Implementierung

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Feedback;

public interface FeedbackService {
	
	//Funktionsweise: abstrakte Methode um ein Feedback zu persistieren
	public Feedback addFeedback(Feedback feedback, HttpServletResponse response) throws IOException;

}
