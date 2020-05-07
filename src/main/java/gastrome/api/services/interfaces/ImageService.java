package gastrome.api.services.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

public interface ImageService {

	public byte[] compressJpgImageReturnAsByteArray(InputStream isChickenBagel) throws IOException;

	public byte[] compressJpgImageReturnAsByteArray(BufferedImage img) throws IOException;

	public void addImageToResponse(byte[] imageAsBytes, HttpServletResponse response) throws IOException;

}
