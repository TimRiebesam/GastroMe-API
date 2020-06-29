package gastrome.api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gastrome.api.entities.geocodexyz.GeocodeXyzApiResponse;
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.services.interfaces.GeoService;

@Service
public class GeoServiceImpl implements GeoService{
	
	@Value("${gastrome.config.geo.geonames-api-path-nearbyPostalCodes}")
	private String defaultNearbyPostalCodesApiPath;
	
	@Value("${gastrome.config.geo.geocodexyz-api-path-geocoding}")
	private String defaultGeocodingXyzApiPath;

	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng) {
		return restTemplate.getForObject(defaultNearbyPostalCodesApiPath+"&lat="+lat+"&lng="+lng, GeonamesNearbyPostalCodesResponse.class);
	}
	
	@Override
	public GeocodeXyzApiResponse getGeodataFromAddressXYZ(int plz, String strasse, int hausnummer) {
		return restTemplate.getForObject(
				defaultGeocodingXyzApiPath + strasse + "+" + hausnummer + "+" + plz + "?json=1&region=DE", 
				GeocodeXyzApiResponse.class);
	}
	
}
