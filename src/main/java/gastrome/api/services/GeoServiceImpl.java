package gastrome.api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.services.interfaces.GeoService;

@Service
public class GeoServiceImpl implements GeoService{
	
	@Value("${gastrome.config.geo.geonames-api-path-nearbyPostalCodes}")
	private String defaultNearbyPostalCodesApiPath;

	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng) {
		return restTemplate.getForObject(defaultNearbyPostalCodesApiPath+"&lat="+lat+"&lng="+lng, GeonamesNearbyPostalCodesResponse.class);
	}
	
}
