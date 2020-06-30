package gastrome.api.services;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import gastrome.api.services.interfaces.QrCodeService;

//Autor: Daniel Sachse (Quelle: https://gist.github.com/wombat/8825038)
//Leichte Änderungen von Tim Riebesam
//Diese Klasse implementiert das QrCodeService-Interface mit den unimplementierten Methoden

@Service
public class QrCodeServiceImpl implements QrCodeService {

	@Value("${gastrome.config.qr.logo-resource-path}")
    private String logoResourcePath;
	
	@Value("${gastrome.config.qr.height}")
    private int height;
	
	@Value("${gastrome.config.qr.width}")
    private int width;

	//Funktionsweise: Diese Methode erstellt aus einen übergebenen String ein QR-Code mit einem Overlay-Bild/Logo in der Mitte des Codes. Farben sind Orange-Weiß.
	// Durch die Variable sw=true wird ein QR-Code in Schwarz-Weiß und ohne Logo in der Mitte erzeugt.
	// Für genauere Infos siehe Kommentare innerhalb der Methode
	@Override
    public byte[] generate(String content, boolean sw) throws Exception {
        // Create new configuration that specifies the error correction
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            // Create a qr code with the url as content and a size of WxH px
            bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            // Load QR image
            int color = (sw == true) ? Colors.BLACK.argb : Colors.ORANGE.argb;
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig(color));

            // Load logo image
            BufferedImage overly = getOverly();

            // Calculate the delta height and width between QR code and logo
            int deltaHeight = qrImage.getHeight() - overly.getHeight();
            int deltaWidth = qrImage.getWidth() - overly.getWidth();

            // Initialize combined image
            BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();

            // Write QR code to new image at position 0/0
            g.drawImage(qrImage, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            // Write logo into combine image at position (deltaWidth / 2) and
            // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
            // the same space for the logo to be centered
            if(sw != true)
            	g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

            // Write combined image as PNG to OutputStream
            ImageIO.write(combined, "png", os);
            // Return ByteArray
            return os.toByteArray();

        } catch (Exception e) {
            throw e;
        }
    }

    private BufferedImage getOverly() throws Exception {
    	Resource resource = new ClassPathResource(logoResourcePath);
		InputStream is = resource.getInputStream();
		if(resource.exists()) 
			return ImageIO.read(is);
    	throw new Exception("Overlay Bild nicht gefunden!");
    }

    private MatrixToImageConfig getMatrixConfig(int color) {
        // ARGB Colors
        // Check Colors ENUM
        return new MatrixToImageConfig(color, Colors.WHITE.getArgb());
    }

    public enum Colors {

        BLUE(0xFF40BAD0),
        RED(0xFFE91C43),
        PURPLE(0xFF8A4F9E),
        ORANGE(0xFFf29f05),
        WHITE(0xFFFFFFFF),
        BLACK(0xFF000000);

        private final int argb;

        Colors(final int argb){
            this.argb = argb;
        }

        public int getArgb(){
            return argb;
        }
    }
}