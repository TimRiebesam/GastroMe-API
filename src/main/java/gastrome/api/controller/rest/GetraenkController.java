package gastrome.api.controller.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gastrome.api.services.interfaces.GetraenkService;
import gastrome.api.services.interfaces.ImageService;

@RestController
public class GetraenkController {

	@Autowired
	GetraenkService getraenkService;

	@Autowired
	ImageService imageService;

	@GetMapping(path= {"getraenk/{getraenkId}/bild"})
	public void getBild(@PathVariable UUID getraenkId, HttpServletResponse response) throws IOException {
		imageService.addImageToResponse(getraenkService.getBild(getraenkId), response);
	}

}
