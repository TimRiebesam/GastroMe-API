package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Gast;
import gastrome.api.entities.Rechnung;
import gastrome.api.entities.Tisch;

//Autor: Tim Riebesam, Tim Bayer
//Dieses Interface definiert die erfolderlichen Methoden einer TischService Implementierung

public interface TischService {

	//Funktionsweise: abstrakte Methode um die Tische eines Restaurant zu beschaffen
	public List<Tisch> getTischeByRestaurantId(UUID restaurantID, HttpServletResponse response) throws IOException;
	
	//Funktionsweise: abstrakte Methode um einen Gast einem Tisch hinzuzufügen
	public void addGast(Gast gast, UUID tisch, HttpServletResponse response) throws IOException;
	
	//Funktionsweise: abstrakte Methode um ein Tisch zu erzeugen und mit einem Restaurant zu verknüpfen
	public Tisch addTisch(UUID restaurantId, HttpServletResponse response, String beschreibung) throws IOException;
	
	//Funktionsweise: abstrakte Methode um die Gästeliste eines Tisches zu leeren
	public void clearGaeste(UUID tischId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um einen QR Code eines Tisches zu erstellen und zu liefern
	public void addQrCodeToResponse(UUID tischId, HttpServletResponse response, boolean sw) throws Exception;

	//Funktionsweise: abstrakte Methode um die neuste Rechnung eines Tisches zu beschaffen
	public Rechnung getLatestRechnungForTisch(UUID tischId);

	//Funktionsweise: abstrakte Methode um den Kellner-gerufen Status zu setzen (true)
	public void callKellner(UUID tischId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um den Kellner-gerufen Status zu setzen (false)
	public Tisch kellnerCalledDone(UUID tischId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um einen Tisch zu löschen
	public void deleteTisch(UUID tischId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um eine Tischbeschreibung zu aktualisieren
	public Tisch updateTisch(UUID tischId, HttpServletResponse response, String beschreibung) throws IOException;
}
