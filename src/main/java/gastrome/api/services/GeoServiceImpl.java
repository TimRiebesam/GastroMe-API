package gastrome.api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gastrome.api.entities.geocodexyz.GeocodeXyzApiResponse;
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.services.interfaces.GeoService;

//Autor: Tim Riebesam
//Diese Klasse implementiert das GeoService-Interface mit den unimplementierten Methoden

@Service
public class GeoServiceImpl implements GeoService{
	
	@Value("${gastrome.config.geo.geonames-api-path-nearbyPostalCodes}")
	private String defaultNearbyPostalCodesApiPath;
	
	@Value("${gastrome.config.geo.geocodexyz-api-path-geocoding}")
	private String defaultGeocodingXyzApiPath;

	RestTemplate restTemplate = new RestTemplate();
	
	//Funktionsweise: Es wird eine Get-Anfrage an die API von geonames.org gesendet, welche aus den übergebenen Parametern erstellt wird. Rückgabewert ist ein GeonamesNearbyPostalCodesResponse. 
	@Override
	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng) {
		return restTemplate.getForObject(defaultNearbyPostalCodesApiPath+"&lat="+lat+"&lng="+lng, GeonamesNearbyPostalCodesResponse.class);
	}
	
	//Funktionsweise: Es wird eine Get-Anfrage an die API von geocode.xyz gesendet, welche aus den übergebenen Parametern erstellt wird. Rückgabewert ist ein GeocodeXyzApiResponse. 
	@Override
	public GeocodeXyzApiResponse getGeodataFromAddressXYZ(int plz, String strasse, int hausnummer) {
		return restTemplate.getForObject(
				defaultGeocodingXyzApiPath + strasse + "+" + hausnummer + "+" + plz + "?json=1&region=DE", 
				GeocodeXyzApiResponse.class);
	}
	
}
