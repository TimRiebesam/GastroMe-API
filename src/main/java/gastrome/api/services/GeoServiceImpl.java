package gastrome.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.entities.positionstack.PositionstackGeoApiData;
import gastrome.api.entities.positionstack.PositionstackGeoApiForwardResponse;
import gastrome.api.services.interfaces.GeoService;

@Service
public class GeoServiceImpl implements GeoService{
	
	@Value("${gastrome.config.geo.geonames-api-path-nearbyPostalCodes}")
	private String defaultNearbyPostalCodesApiPath;
	
	@Value("${gastrome.config.geo.positionstack-api-path-geocoding}")
	private String defaultGeocodingApiPath;

	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng) {
		return restTemplate.getForObject(defaultNearbyPostalCodesApiPath+"&lat="+lat+"&lng="+lng, GeonamesNearbyPostalCodesResponse.class);
	}
	
	@Override
	public PositionstackGeoApiData getGeodataFromAddress(int plz, String strasse, int hausnummer) {
		PositionstackGeoApiForwardResponse response = restTemplate.getForObject(defaultGeocodingApiPath+"&query="+strasse+"%20"+hausnummer+","+plz+"&country=DE", PositionstackGeoApiForwardResponse.class);
		
		List<PositionstackGeoApiData> responseData = response.getData();
		PositionstackGeoApiData locationData = null;
		
		for(int i = 0; i < responseData.size(); i++) {
			if(responseData.get(i).getConfidence() > 0.5 && Integer.parseInt(responseData.get(i).getPostal_code()) == plz && responseData.get(i).getStreet().equals(strasse)) {
				locationData = responseData.get(i);
			}
		}
		
		return locationData;
	}
	
}
