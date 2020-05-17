package gastrome.api.services.interfaces;

public interface QrCodeService {

	public byte[] generate(String content) throws Exception;

}
