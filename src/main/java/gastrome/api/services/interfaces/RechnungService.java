package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.GetraenkOrder;
import gastrome.api.entities.Rechnung;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer RestaurantService Implementierung

public interface RechnungService {

	//Funktionsweise: abstrakte Methode um eine Speise hinzuzufügen
	public Rechnung addSpeise(UUID rechnungId, UUID speiseId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um ein Getränk hinzuzufügen
	public Rechnung addGetraenk(UUID rechnungId, UUID getraenkId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um eine Rechnung zu bezahlen
	public Rechnung payRechnung(UUID rechnungId, HttpServletResponse response) throws IOException;

	//Funktionsweise: abstrakte Methode um eine Order zu akzeptieren
	public GetraenkOrder acceptOrder(UUID fromString, HttpServletResponse response) throws IOException;

}
