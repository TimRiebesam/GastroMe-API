package gastrome.api.services.interfaces;

import gastrome.api.entities.geocodexyz.GeocodeXyzApiResponse;
import gastrome.api.entities.geonames.GeonamesNearbyPostalCodesResponse;

public interface GeoService {

	public GeonamesNearbyPostalCodesResponse getNearbyPostalCodesByLatAndLng(double lat, double lng);

	public GeocodeXyzApiResponse getGeodataFromAddressXYZ(int plz, String strasse, int hausnummer);

}
