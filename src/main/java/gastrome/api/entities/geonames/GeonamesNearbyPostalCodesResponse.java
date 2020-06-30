package gastrome.api.entities.geonames;

//Autor: Tim Riebesam
//Diese Klasse stellt den Response einer HTTP-Anfrage an die geonames.org API dar.
//Für Details: https://www.geonames.org/export/web-services.html#findNearbyPostalCodes

import java.util.List;

public class GeonamesNearbyPostalCodesResponse {

	private List<GeonamesPostalCode> postalCodes;

	public List<GeonamesPostalCode> getPostalCodes() {
		return postalCodes;
	}

	public void setPostalCodes(List<GeonamesPostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	} 
	
}
