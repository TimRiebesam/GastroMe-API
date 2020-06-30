package gastrome.api.services.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer ImageService Implementierung

public interface ImageService {

	//Funktionsweise: abstrakte Methode um ein Jpg Bild in ein ByteArray umzuwandeln
	public byte[] compressJpgImageReturnAsByteArray(InputStream inputStream) throws IOException;

	//Funktionsweise: abstrakte Methode um ein Jpg Bild in ein ByteArray umzuwandeln
	public byte[] compressJpgImageReturnAsByteArray(BufferedImage img) throws IOException;

	//Funktionsweise: abstrakte Methode um ein Bild einem HttpServletResponse hinzuzufügen
	public void addImageToResponse(byte[] imageAsBytes, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um ein base64String in ein ByteArray umzuwandeln
	byte[] base64StringToByteArray(String base64String) throws IOException;

}
