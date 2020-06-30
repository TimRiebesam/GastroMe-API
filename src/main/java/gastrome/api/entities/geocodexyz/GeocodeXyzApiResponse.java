package gastrome.api.entities.geocodexyz;

//Autor: Tim Riebesam
//Diese Klasse stellt den Response einer HTTP-Anfrage an die geocodexyz.com API dar.
//Für Details: https://geocode.xyz/api

public class GeocodeXyzApiResponse {
	
	private GeocodeXyzApiResponseStandard standard;
	
	private Double longt;
	
	private Double latt;
	
	private GeocodeXyzApiResponseError error;

	public GeocodeXyzApiResponseStandard getStandard() {
		return standard;
	}

	public void setStandard(GeocodeXyzApiResponseStandard standard) {
		this.standard = standard;
	}

	public Double getLongt() {
		return longt;
	}

	public void setLongt(Double longt) {
		this.longt = longt;
	}

	public Double getLatt() {
		return latt;
	}

	public void setLatt(Double latt) {
		this.latt = latt;
	}

	public GeocodeXyzApiResponseError getError() {
		return error;
	}

	public void setError(GeocodeXyzApiResponseError error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		return standard.getCountryname() + " " + standard.getPostal() + " " + standard.getCity() + ", " + standard.getAddresst() + " " + standard.getStnumber();
	}
	
}
