package gastrome.api.services.interfaces;

import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;

public interface GeoService {

	GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng);

}
