package gastrome.api.services;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gastrome.api.services.interfaces.ImageService;

import java.util.Base64;
import java.util.Base64.Decoder;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Value("${gastrome.config.images.scaledWidth}")
    private int scaledWidth;
	
	@Override
	public byte[] compressJpgImageReturnAsByteArray(InputStream isChickenBagel) throws IOException {
		BufferedImage img = ImageIO.read(isChickenBagel);
		return compressJpgImageReturnAsByteArray(img);
	}

	@Override
	public byte[] compressJpgImageReturnAsByteArray(BufferedImage img) throws IOException {
		BufferedImage compressedImg = cropImage(img);
		compressedImg = scaleImage(compressedImg);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(compressedImg, "jpg", baos);
		
		return baos.toByteArray();
	}
	
	private BufferedImage scaleImage(BufferedImage originalImage) {
		// Höhe -1: If either width or height is a negative number then a value is substituted to maintain the aspect ratio of the original image dimensions.
		Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledWidth, Image.SCALE_SMOOTH);

		BufferedImage outImg = new BufferedImage(scaledWidth, scaledWidth, BufferedImage.TYPE_INT_RGB);
		Graphics g = outImg.getGraphics();
		g.drawImage(scaledImage, 0, 0, null);
		g.dispose();
		
		return outImg;
	}
	
	private BufferedImage cropImage(BufferedImage originalImage) {
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();

        int targetWidth, targetHeight, xc, yc;
        
        if(width > height) {
        	targetWidth = height;
            targetHeight = height;
            // Coordinates of the image's middle
            xc = (width/2) - (height/2);
            yc = 0;
        }
        
        else {
        	targetWidth = width;
            targetHeight = width;
            // Coordinates of the image's middle
            xc = 0;
            yc = (height/2) - (width/2);
		}

        // Crop
        BufferedImage croppedImage = originalImage.getSubimage(
                        xc, 
                        yc,
                        targetWidth, // widht
                        targetHeight // height
        );
        return croppedImage;
	}
	
	@Override
	public void addImageToResponse(byte[] imageAsBytes, HttpServletResponse response) throws IOException {
		if(imageAsBytes != null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(imageAsBytes);;
			IOUtils.copy(bis, response.getOutputStream());
			response.flushBuffer();
		}
		else {
			response.sendError(404, "Kein Bild gefunden!");
		}
	}
	
	@Override
	public byte[] base64StringToByteArray(String base64String) throws IOException {
		String[] parts = base64String.split(",");
		String imageString = parts[1];

		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte;
		
		Decoder decoder = Base64.getDecoder();
		imageByte = decoder.decode(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();
		
		return compressJpgImageReturnAsByteArray(image);
	}

}
