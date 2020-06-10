package gastrome.api.services.interfaces;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import gastrome.api.entities.Rechnung;

public interface RechnungService {

	public Rechnung addSpeise(UUID rechnungId, UUID speiseId, HttpServletResponse response) throws IOException;

	public Rechnung addGetraenk(UUID rechnungId, UUID getraenkId, HttpServletResponse response) throws IOException;

	public Rechnung payRechnung(UUID rechnungId, HttpServletResponse response) throws IOException;

}
