package gastrome.api.services.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

	public byte[] compressJpgImageReturnAsByteArray(InputStream isChickenBagel) throws IOException;

	public byte[] compressJpgImageReturnAsByteArray(BufferedImage img) throws IOException;

}
