package gastrome.api.entities.geonames;

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
