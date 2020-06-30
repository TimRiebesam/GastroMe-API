package gastrome.api.entities.geocodexyz;

//Autor: Tim Riebesam
//Diese Klasse stellt einen Teil der Response einer HTTP-Anfrage an die geocodexyz.com API dar.
//Für Details: https://geocode.xyz/api

public class GeocodeXyzApiResponseStandard {

	private String stnumber;
	
	private String addresst;
	
	private Integer postal;
	
	private String region;
	
	private String prov;
	
	private String city;
	
	private String countryname;
	
	private Double confidence;

	public String getStnumber() {
		return stnumber;
	}

	public void setStnumber(String stnumber) {
		this.stnumber = stnumber;
	}

	public String getAddresst() {
		return addresst;
	}

	public void setAddresst(String addresst) {
		this.addresst = addresst;
	}

	public Integer getPostal() {
		return postal;
	}

	public void setPostal(Integer postal) {
		this.postal = postal;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = Double.parseDouble(confidence);
	}
	
}
