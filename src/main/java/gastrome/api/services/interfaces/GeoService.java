package gastrome.api.services.interfaces;

import gastrome.api.entities.geocodexyz.GeocodeXyzApiResponse;
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;

//Autor: Tim Riebesam
//Dieses Interface definiert die erfolderlichen Methoden einer GeoService Implementierung

public interface GeoService {

	//Funktionsweise: abstrakte Methode um Postleitzahlen in Form eines GeonamesNearbyPostalCodesResponse aus Längen- und Breitengraden zu erfahren
	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng);

	//Funktionsweise: abstrakte Methode um Geodaten in Form eines GeocodeXyzApiResponse aus einer plz, strasse und hausnumemr zu erfahren
	public GeocodeXyzApiResponse getGeodataFromAddressXYZ(int plz, String strasse, int hausnummer);

}
