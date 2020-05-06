package gastrome.api.services.interfaces;

import java.io.File;
import java.io.IOException;

public interface ImageService {

	public byte[] compressJpgImageReturnAsByteArray(File file) throws IOException;

}
