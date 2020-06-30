package gastrome.api.services.interfaces;

import java.util.UUID;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer SpeiseService Implementierung

public interface SpeiseService {
	
	//Funktionsweise: abstrakte Methode um das Bild einer Speise zu erhalten
	public byte[] getBild(UUID speiseId);

}
