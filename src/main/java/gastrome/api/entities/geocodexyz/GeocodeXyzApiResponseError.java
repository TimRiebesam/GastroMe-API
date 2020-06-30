package gastrome.api.entities.geocodexyz;

//Autor: Tim Riebesam
//Diese Klasse stellt einen Teil der Response einer HTTP-Anfrage an die geocodexyz.com API dar.
//Für Details: https://geocode.xyz/api

public class GeocodeXyzApiResponseError {

	private String description;
	
	private String code;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
