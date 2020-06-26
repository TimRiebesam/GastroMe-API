package gastrome.api.services.interfaces;

import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;
import gastrome.api.entities.positionstack.PositionstackGeoApiData;

public interface GeoService {

	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng);

	public PositionstackGeoApiData getGeodataFromAddress(int plz, String strasse, int hausnummer);

}
