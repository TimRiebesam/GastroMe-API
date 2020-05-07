package gastrome.api.entities.geonames;

public class GeonamesPostalCode {

	private String adminCode1;
	
	private String adminCode2;
	
	private String adminCode3;
	
	private String adminName1;
	
	private String adminName2;
	
	private String adminName3;
	
	private double lng;
	
	private double lat;
	
	private double distance;
	
	private String countryCode;
	
	private int postalCode;
	
	private String placeName;

	public String getAdminCode1() {
		return adminCode1;
	}

	public void setAdminCode1(String adminCode1) {
		this.adminCode1 = adminCode1;
	}

	public String getAdminCode2() {
		return adminCode2;
	}

	public void setAdminCode2(String adminCode2) {
		this.adminCode2 = adminCode2;
	}

	public String getAdminCode3() {
		return adminCode3;
	}

	public void setAdminCode3(String adminCode3) {
		this.adminCode3 = adminCode3;
	}

	public String getAdminName1() {
		return adminName1;
	}

	public void setAdminName1(String adminName1) {
		this.adminName1 = adminName1;
	}

	public String getAdminName2() {
		return adminName2;
	}

	public void setAdminName2(String adminName2) {
		this.adminName2 = adminName2;
	}

	public String getAdminName3() {
		return adminName3;
	}

	public void setAdminName3(String adminName3) {
		this.adminName3 = adminName3;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
}
