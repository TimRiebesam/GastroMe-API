package gastrome.api.services.interfaces;

import java.util.UUID;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer ImageService Implementierung

public interface GetraenkService {
	
	//Funktionsweise: abstrakte Methode um das Bild eines Getränks zu erhalten
	public byte[] getBild(UUID getraenkId);

}
