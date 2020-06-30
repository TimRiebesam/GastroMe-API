package gastrome.api.services.interfaces;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer QrCodeService Implementierung

public interface QrCodeService {

	//Funktionsweise: abstrakte Methode um einen QR-Code zu generieren
	public byte[] generate(String content, boolean sw) throws Exception;

}
