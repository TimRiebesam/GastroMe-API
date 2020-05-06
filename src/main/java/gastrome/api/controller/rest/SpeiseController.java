package gastrome.api.controller.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.services.interfaces.ImageService;
import gastrome.api.services.interfaces.SpeiseService;

@RestController
public class SpeiseController {

	@Autowired
	SpeiseService speiseService;

	@Autowired
	ImageService imageService;

	@GetMapping(path= {"speise/{speiseId}/bild"})
	public void getBild(@PathVariable UUID speiseId, HttpServletResponse response) throws IOException {
		byte[] imageAsBytes = speiseService.getBild(speiseId);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageAsBytes);;
		IOUtils.copy(bis, response.getOutputStream());
		response.flushBuffer();
	}

}
