package gastrome.api.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;

import gastrome.api.entities.Allergen;
import gastrome.api.entities.Bewertung;
import gastrome.api.entities.Getraenk;
import gastrome.api.entities.PLZ;
import gastrome.api.entities.Rechnung;
import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Rezession;
import gastrome.api.entities.Speise;
import gastrome.api.entities.Speisekarte;
import gastrome.api.entities.Standort;
import gastrome.api.entities.Tisch;
import gastrome.api.repositories.AllergenRepository;
import gastrome.api.repositories.BewertungRepository;
import gastrome.api.repositories.GastRepository;
import gastrome.api.repositories.GetraenkRepository;
import gastrome.api.repositories.PLZRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.RezessionRepository;
import gastrome.api.repositories.SpeiseRepository;
import gastrome.api.repositories.SpeisekarteRepository;
import gastrome.api.repositories.StandortRepository;
import gastrome.api.repositories.TischRepository;
import gastrome.api.services.interfaces.ImageService;

@Component
public class DatabaseDummyInit {

	@Autowired
	AllergenRepository allergenRepository;

	@Autowired
	BewertungRepository bewertungRepository;

	@Autowired
	GetraenkRepository getraenkRepository;

	@Autowired
	PLZRepository plzRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	RezessionRepository rezessionRepository;

	@Autowired
	SpeiseRepository speiseRepository;

	@Autowired
	SpeisekarteRepository speisekarteRepository;

	@Autowired
	StandortRepository standortRepository;
	
	@Autowired
	TischRepository tischRepository;
	
	@Autowired
	GastRepository gastRepository;

	@Autowired
	ImageService imageService;
	
	
	Allergen gluten;
	Allergen krebstiere;
	Allergen ei;
	Allergen fisch;
	Allergen erdnuesse;
	Allergen sojabohnen;
	Allergen milch;
	Allergen schalenfrüchte;
	Allergen sellerie;
	Allergen senf;
	Allergen sesamsamen;
	Allergen schwefel_sulfite;
	Allergen lupinen;
	Allergen weichtiere;
	

	@PostConstruct
	public void loadDummyDataIntoDatabase() throws IOException {
		System.out.println("DB wird gelöscht...");
		clearDatabase();
		System.out.println("Allergene in DB speichern...");
		addAllergene();
		System.out.println("Dummydaten in DB speichern...");
		fillDatabaseWithInitData();
	}

	private void clearDatabase() {
		allergenRepository.deleteAll();
		bewertungRepository.deleteAll();
		getraenkRepository.deleteAll();
		plzRepository.deleteAll();
		restaurantRepository.deleteAll();
		rezessionRepository.deleteAll();
		speiseRepository.deleteAll();
		speisekarteRepository.deleteAll();
		standortRepository.deleteAll();
	}
	
	private void addAllergene() {
		gluten = allergenRepository.save(new Allergen("Glutenhaltiges Getreide", "(Weizen, Dinkel, Roggen, Gerste, Hafer oder Hybridstämme davon)", null));
		krebstiere = allergenRepository.save(new Allergen("Krebstiere", "", null));
		ei = allergenRepository.save(new Allergen("Eier", "", null));;
		fisch = allergenRepository.save(new Allergen("Fisch", "", null));
		erdnuesse = allergenRepository.save(new Allergen("Erdnüsse", "", null));
		sojabohnen = allergenRepository.save(new Allergen("Sojabohnen", "", null));
		milch = allergenRepository.save(new Allergen("Milch und Milchprodukte", "einschließlich Laktose", null));
		schalenfrüchte = allergenRepository.save(new Allergen("Schalenfrüchte ", "Mandeln, Haselnüsse, Walnüsse, Kaschunüsse, Pecannüsse, Paranüsse, Pistazien, Macadamia- oder Queenslandnüsse", null));
		sellerie = allergenRepository.save(new Allergen("Sellerie", "", null));
		senf = allergenRepository.save(new Allergen("Senf", "", null));
		sesamsamen = allergenRepository.save(new Allergen("Sesamsamen", "", null));
		schwefel_sulfite = allergenRepository.save(new Allergen("Schwefeldioxid und Sulfite", "in einer Konzentration von mehr als 10 mg/kg oder 10mg/l", null));
		lupinen = allergenRepository.save(new Allergen("Lupinen", "", null));
		weichtiere = allergenRepository.save(new Allergen("Weichtiere", "", null));
	}
	
	private void fillDatabaseWithInitData() throws IOException {
		addCafePalaver();
		addCafePerlbohne();
		addOxfordPub();
		
		Restaurant woodie = new Restaurant("Woodie", "Café in Holzoptik in der Nordstadt,bekannt für Kaffee, Kakao und\r\nSüßes Gebäck...", "GastroMeWaiterTim@gmail.com");
		woodie.setBild(loadImageFromResources("restaurants/oxford"));
		woodie = restaurantRepository.save(woodie);
		
		Restaurant burgerParadise = new Restaurant("Burger Paradise", "Burger Restaurant in der Innenstadt, bekannt für Burger, Fritten und Eistee...", "GastroMeWaiterTim@gmail.com");
		burgerParadise.setBild(loadImageFromResources("restaurants/deli"));
		burgerParadise = restaurantRepository.save(burgerParadise);
		
		addCafeSimple();
	}
	
	private byte[] loadImageFromResources(String imagePath) throws IOException {
		Resource resource = new ClassPathResource("static/img/" + imagePath + ".jpg");
		InputStream is = resource.getInputStream();
		if(resource.exists()) 
			return imageService.compressJpgImageReturnAsByteArray(is);
		return null;
	}
	
	private String loadTextFromResources(String txtPath) throws IOException {
		Resource resource = new ClassPathResource("static/text/" + txtPath + ".txt");
		InputStream is = resource.getInputStream();
		
		if(resource.exists()) {
			ByteSource byteSource = new ByteSource() {
	            @Override
	            public InputStream openStream() throws IOException {
	                return is;
	            }
	        };
	        return byteSource.asCharSource(Charsets.UTF_8).read();
		}
		
		return null;
	}
	
	private Restaurant saveRestaurant(Restaurant restaurant, String resourcePath) throws IOException {
		restaurant.setBild(loadImageFromResources(resourcePath));
		return restaurantRepository.save(restaurant);
	}
	
	private Standort saveStandort(Standort standort, PLZ plz, Restaurant restaurant) {
		plz = plzRepository.save(plz);
		standort.setPlz(plz);
		standort.setRestaurant(restaurant);
		return standortRepository.save(standort);
	}
	
	private Speisekarte saveSpeisekarte(Speisekarte speisekarte, Restaurant restaurant) {
		speisekarteRepository.save(speisekarte);
		speisekarte.setRestaurant(restaurant);
		return speisekarteRepository.save(speisekarte);
	}
	
	private Speisekarte updateSpeisekarte(Speisekarte speisekarte, ArrayList<Getraenk> getraenke, ArrayList<Speise> speisen) {
		speisekarte.setSpeisen(speisen);
		speisekarte.setGetraenke(getraenke);
		return speisekarteRepository.save(speisekarte);
	}
	
	private Speise saveSpeise(Speise speise, Speisekarte speisekarte, String resourcePath, List<Allergen> allergene) throws IOException {
		speise.setSpeisekarte(speisekarte);
		speise.setBild(loadImageFromResources(resourcePath));
		speise = speiseRepository.save(speise);
		speise.setAllergene(allergene);
		return speiseRepository.save(speise);
	}
	
	private Getraenk saveGetraenk(Getraenk getraenk, Speisekarte speisekarte, String resourcePath, List<Allergen> allergene) throws IOException {
		getraenk.setSpeisekarte(speisekarte);
		getraenk.setBild(loadImageFromResources(resourcePath));
		getraenk = getraenkRepository.save(getraenk);
		getraenk.setAllergene(allergene);
		return getraenkRepository.save(getraenk);
	}
	
	
	private Bewertung saveBewertung(Bewertung bewertung, Rezession rezession, Restaurant restaurant) {
		bewertung = bewertungRepository.save(bewertung);
		bewertung.setRezession(rezession);
		rezession.setRestaurant(restaurant);
		rezessionRepository.save(rezession);
		return bewertungRepository.save(bewertung);
	}
	
	private Tisch saveTisch(Tisch tisch, Rechnung rechnung, Restaurant restaurant) throws IOException {
		tisch.setRestaurant(restaurant);
		return tischRepository.save(tisch);
	}

	private void addCafePalaver() throws IOException {
		Restaurant cafePalaver = saveRestaurant(
				new Restaurant("café palaver", loadTextFromResources("restaurants/palaver"), "GastroMeWaiterTim@gmail.com"),
				"restaurants/palaver");
		
		Standort standortCafePalaver = saveStandort(
				new Standort("Steinstrasse", "23", 49.0068087, 8.4067684, "im Gewerbehof"),
				new PLZ(76133, "Karlsruhe"),
				cafePalaver
				);
		
		Speisekarte speisekarteCafePalaver = saveSpeisekarte(
				new Speisekarte(),
				cafePalaver);
		
		Speise mini = saveSpeise(
				new Speise("Mini", "helles Brötchen, Butter, Fruchtaufstrich oder Honig", 2.9, null, true, false, null),
				speisekarteCafePalaver,
				"speisen/palaver_mini",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten)));
		
		Speise petit = saveSpeise(
				new Speise("Petit Déjeuner", "Croissants, Butter und Fruchtaufstrich oder Honig", 3.2, null, true, false, null),
				speisekarteCafePalaver,
				"speisen/palaver_petit",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten)));
		
		Speise breakfast = saveSpeise(
				new Speise("Breakfast", "2 Spiegeleier mit Frühstücksspeck, Orangenmarmelade, Butter & Brötchen", 7.9, null, false, false, null),
				speisekarteCafePalaver,
				"speisen/palaver_breakfast",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten, ei)));
		
		Speise bauarbeiterin = saveSpeise(
				new Speise("Bauarbeiterin", "Putensalami, Schinken, Käse, Ei, Butter, 1 Scheibe Brot & 2 Brötchen", 8.5, null, false, false, null),
				speisekarteCafePalaver,
				"speisen/palaver_bauarbeiterin",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten, ei)));

		Speise muesli = saveSpeise(
				new Speise("Müsli", "mit Joghurt, Nüssen und Früchten", 5.2, null, true, true, null),
				speisekarteCafePalaver,
				"speisen/palaver_muesli",
				new ArrayList<Allergen>(Arrays.asList(milch, erdnuesse)));
		
		Speise obstsalat = saveSpeise(
				new Speise("Obstsalat", "nach Saison", 3.2, null, true, true, null),
				speisekarteCafePalaver,
				"speisen/palaver_obst",
				null);
		
		Getraenk espresso = saveGetraenk(
				new Getraenk("Espresso", "von der Karlsruher Rösterei Tostino", 2, null, true, true, null),
				speisekarteCafePalaver,
				"getraenk/espresso",
				null);
		
		Getraenk cappuccino = saveGetraenk(
				new Getraenk("Cappuccino", "von der Karlsruher Rösterei Tostino", 2.9, null, true, false, null),
				speisekarteCafePalaver,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk latte = saveGetraenk(
				new Getraenk("Latte Macchiato", "von der Karlsruher Rösterei Tostino", 3.5, null, true, false, null),
				speisekarteCafePalaver,
				"getraenk/latte",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk orangensaft = saveGetraenk(
				new Getraenk("Orangensaft 0,4", "ohen Fruchtfleisch, von Jacoby", 5.8, null, true, true, null),
				speisekarteCafePalaver,
				"getraenk/orangensaft",
				null);
		
		Getraenk tee = saveGetraenk(
				new Getraenk("Orangen Tee", "von Lebensbaum oder Yogitea", 2.5, null, true, true, null),
				speisekarteCafePalaver,
				"getraenk/tee",
				null);
		
		Getraenk sekt = saveGetraenk(
				new Getraenk("Sekt", "Hausmarke 0,1 l", 3.2, null, true, true, null),
				speisekarteCafePalaver,
				"getraenk/sekt",
				null);
		
		speisekarteCafePalaver = updateSpeisekarte(
				speisekarteCafePalaver,
				new ArrayList<Getraenk>(Arrays.asList(espresso, cappuccino, latte, orangensaft, tee, sekt)), 
				new ArrayList<Speise>(Arrays.asList(mini, petit, breakfast, bauarbeiterin, muesli, obstsalat)));

		Bewertung cafePalaverBewertung1 = saveBewertung(
				new Bewertung(5, 5, 4, 4, 5),
				new Rezession("Sehr gutes Restaurant, Bagel sind fantastisch!"),
				cafePalaver);
		
		Bewertung cafePalaverBewertung2 = saveBewertung(
				new Bewertung(4, 5, 5, 5, 3),
				new Rezession("Insgesamt super Restaurant. Nachhaltig und Modern!"),
				cafePalaver);
		
		Bewertung cafePalaverBewertung3 = saveBewertung(
				new Bewertung(5, 5, 5, 3, 4),
				new Rezession("Gefällt mir sehr gut, bin hier öfter in der Mittagspause und genieße einen Kaffee."),
				cafePalaver);
		
		Bewertung cafePalaverBewertung4 = saveBewertung(
				new Bewertung(1, 2, 2, 1, 1),
				new Rezession("Versuchen modern zu sein, schaffen es aber nicht! Essen schmeckt nicht gut."),
				cafePalaver);
		
		Bewertung cafePalaverBewertung5 = saveBewertung(
				new Bewertung(4, 3, 4, 5, 2),
				new Rezession("Gut zum essen, Atmosphäre nicht so toll."),
				cafePalaver);
		
		Tisch tisch1 = saveTisch(new Tisch("Tisch 1"), new Rechnung(), cafePalaver);
		Tisch tisch2 = saveTisch(new Tisch("Tisch 2"), new Rechnung(), cafePalaver);
		Tisch tisch3 = saveTisch(new Tisch("Tisch 3"), new Rechnung(), cafePalaver);
		Tisch tisch4 = saveTisch(new Tisch("Tisch 4"), new Rechnung(), cafePalaver);
		Tisch tisch5 = saveTisch(new Tisch("Tisch 5"), new Rechnung(), cafePalaver);
		Tisch tisch6 = saveTisch(new Tisch("Tisch 6"), new Rechnung(), cafePalaver);
	}
	
	private void addCafePerlbohne() throws IOException {
		Restaurant cafePerlbohne = saveRestaurant(
				new Restaurant("Perlbohne", loadTextFromResources("restaurants/perlbohne"), "GastroMeWaiterTim@gmail.com"),
				"restaurants/perlbohne");
		
		Standort standortCafePerlbohne = saveStandort(
				new Standort("Blumenstraße ", "19", 49.0082727, 8.396396, ""),
				new PLZ(76133, "Karlsruhe"),
				cafePerlbohne
				);
		
		Speisekarte speisekarteCafePerlbohne = saveSpeisekarte(
				new Speisekarte(),
				cafePerlbohne);
		
		Getraenk espresso1 = saveGetraenk(
				new Getraenk("Espresso 1 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 1.8, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/espresso",
				null);
		
		Getraenk espresso2 = saveGetraenk(
				new Getraenk("Espresso 2 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.5, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/espresso",
				null);
		
		Getraenk espressoMachhiato1 = saveGetraenk(
				new Getraenk("Espresso Macchiato 1 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.0, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/espresso_mach",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk espressoMachhiato2 = saveGetraenk(
				new Getraenk("Espresso Macchiato 2 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.7, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/espresso_mach",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk cappuccino = saveGetraenk(
				new Getraenk("Cappucchino 1 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.6, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk flatWhite = saveGetraenk(
				new Getraenk("Flat White 2 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 3.2, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk chocolate = saveGetraenk(
				new Getraenk("Heiße Schokolade", "", 2.9, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/hotChocolate",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk tee = saveGetraenk(
				new Getraenk("Teebeutel", "Schlürfel diverse Sorten", 1.9, null, true, true, null),
				speisekarteCafePerlbohne,
				"getraenk/tee",
				null);
		
		speisekarteCafePerlbohne = updateSpeisekarte(
				speisekarteCafePerlbohne,
				new ArrayList<Getraenk>(Arrays.asList(espresso1, espresso2, cappuccino, flatWhite, espressoMachhiato1, espressoMachhiato2, chocolate, tee)), 
				new ArrayList<Speise>(Arrays.asList()));

		Bewertung cafePerlbohneBewertung1 = saveBewertung(
				new Bewertung(5, 5, 5, 4, 5),
				new Rezession("Sehr gutes Café, leckerer Kuchen und hervorragender Kaffee!"),
				cafePerlbohne);
		
		Bewertung cafePerlbohneBewertung2 = saveBewertung(
				new Bewertung(4, 5, 5, 5, 5),
				new Rezession("Hier kann man noch besonderen kaffee trinken!"),
				cafePerlbohne);
		
		Bewertung cafePerlbohneBewertung3 = saveBewertung(
				new Bewertung(4, 5, 5, 5, 4),
				new Rezession("Gefällt mir sehr gut, bin hier öfter in der Mittagspause und genieße einen Kaffee."),
				cafePerlbohne);
		
		Bewertung cafePerlbohneBewertung4 = saveBewertung(
				new Bewertung(5, 5, 5, 5, 5),
				new Rezession("Der beste Kaffee den ich je getrunken habe."),
				cafePerlbohne);
		
		Bewertung cafePerlbohneBewertung5 = saveBewertung(
				new Bewertung(5, 5, 5, 4, 5),
				new Rezession("Lieblingskaffee :)"),
				cafePerlbohne);
		
		Tisch tisch1 = saveTisch(new Tisch("Tisch 1"), new Rechnung(), cafePerlbohne);
		Tisch tisch2 = saveTisch(new Tisch("Tisch 2"), new Rechnung(), cafePerlbohne);
		Tisch tisch3 = saveTisch(new Tisch("Tisch 3"), new Rechnung(), cafePerlbohne);
		Tisch tisch4 = saveTisch(new Tisch("Tisch 4"), new Rechnung(), cafePerlbohne);
		Tisch tisch5 = saveTisch(new Tisch("Tisch 5"), new Rechnung(), cafePerlbohne);
		Tisch tisch6 = saveTisch(new Tisch("Tisch 6"), new Rechnung(), cafePerlbohne);
	}
	
	private void addOxfordPub() throws IOException {
		Restaurant oxfordPub = saveRestaurant(
				new Restaurant("Oxford Pub", loadTextFromResources("restaurants/oxford"), "GastroMeWaiterTim@gmail.com"),
				"restaurants/oxford");
		
		Standort standortOxfordPub = saveStandort(
				new Standort("Fasanenstraße ", "6", 49.0086442, 8.412434, ""),
				new PLZ(76131, "Karlsruhe"),
				oxfordPub
				);
		
		Speisekarte speisekarteOxfordPub = saveSpeisekarte(
				new Speisekarte(),
				oxfordPub);
		
		Speise hamburgerPommes = saveSpeise(
				new Speise("Hamburger mit Pommes", "180g Patty, Salat, Tomaten, Zwiebeln und Spezial-Hamburger-Soße + 150g Pommes", 4.9, null, false, false, null),
				speisekarteOxfordPub,
				"speisen/burger_pommes",
				new ArrayList<Allergen>(Arrays.asList(gluten)));
		
		Speise cheeseburgerPommes = saveSpeise(
				new Speise("Cheeseburger mit Pommes", "180g Patty, Käse, Salat, Tomaten, Zwiebeln und Spezial-Hamburger-Soße + 150gPommes", 5.4, null, false, false, null),
				speisekarteOxfordPub,
				"speisen/cheeseburger_pommes",
				new ArrayList<Allergen>(Arrays.asList(gluten, milch)));
		
		Speise farmerburgerPommes = saveSpeise(
				new Speise("Farmerburger mit Pommes", "180g Patty, Cheddar, Spiegelei, Bacon und Röstzwiebeln + 150gPommes", 7.9, null, false, false, null),
				speisekarteOxfordPub,
				"speisen/farmerburger_pommes",
				new ArrayList<Allergen>(Arrays.asList(gluten, milch)));
		
		Speise doubleOxPommes = saveSpeise(
				new Speise("Double OX-Burger mit Pommes", "2 x 180g Patty, Cheddar, Bacon und gegrillte Peperoni + 150gPommes", 9.9, null, false, false, null),
				speisekarteOxfordPub,
				"speisen/doubleox_pommes",
				new ArrayList<Allergen>(Arrays.asList(gluten, milch)));
		
		Speise falafelburgerPommes = saveSpeise(
				new Speise("Falafelburger mit Pommes", "Kichererbsen-Gemüse-Patty, Kichererbsen-Kokos-Soße und Rucola", 6.9, null, true, true, null),
				speisekarteOxfordPub,
				"speisen/falafel_pommes",
				new ArrayList<Allergen>(Arrays.asList(gluten)));
		
		Speise schnitzelPommes = saveSpeise(
				new Speise("Schnitzel mit Pommes oder Spätzle", "Braten- oder Rahmsoße", 7.9, null, false, false, null),
				speisekarteOxfordPub,
				"speisen/schnitzel_pommes",
				new ArrayList<Allergen>(Arrays.asList(gluten)));
		
		Getraenk benShaws = saveGetraenk(
				new Getraenk("Ben Shaws Cloudy Lemonade", "Englische Zitronenlimonade 0,33l", 3.0, null, true, true, null),
				speisekarteOxfordPub,
				"getraenk/ben_shaws",
				null);
		
		Getraenk jamaicaGinger = saveGetraenk(
				new Getraenk("Old Jamaica Ginger Beer", "0,33l", 3.0, null, true, true, null),
				speisekarteOxfordPub,
				"getraenk/old_jamaica",
				null);
		
		Getraenk craftbeer = saveGetraenk(
				new Getraenk("Ü Craftbeer", "Überraschungs-Craftbier 0,33l", 3.0, null, true, true, null),
				speisekarteOxfordPub,
				"getraenk/craftbeer",
				null);
		
		Getraenk augustiener = saveGetraenk(
				new Getraenk("Augustiener Helles", "0,5l", 3.9, null, true, true, null),
				speisekarteOxfordPub,
				"getraenk/augustiener",
				null);
		
		speisekarteOxfordPub = updateSpeisekarte(
				speisekarteOxfordPub,
				new ArrayList<Getraenk>(Arrays.asList(benShaws, jamaicaGinger, craftbeer, augustiener)), 
				new ArrayList<Speise>(Arrays.asList(hamburgerPommes, cheeseburgerPommes, farmerburgerPommes, doubleOxPommes, falafelburgerPommes, schnitzelPommes)));

		Bewertung oxfordPubBewertung1 = saveBewertung(
				new Bewertung(3, 4, 4, 5, 4),
				new Rezession("Günstig und lecker, perfekt für Studenten."),
				oxfordPub);
		
		Bewertung oxfordPubBewertung2 = saveBewertung(
				new Bewertung(4, 5, 5, 5, 4),
				new Rezession("Faire Preis und ordentliche Portionen."),
				oxfordPub);
		
		Bewertung oxfordPubBewertung3 = saveBewertung(
				new Bewertung(3, 4, 4, 5, 3),
				new Rezession("Das Essen ist ok aber absolut nichts besonderes. Preis ist wirklich gut, aber da zahle ich lieber etwas mehr für einen richtigen Burger."),
				oxfordPub);
		
		Bewertung oxfordPubBewertung4 = saveBewertung(
				new Bewertung(1, 2, 2, 3, 2),
				new Rezession("Billig-Burger! Nichts für mich!"),
				oxfordPub);
		
		Bewertung oxfordPubBewertung5 = saveBewertung(
				new Bewertung(4, 5, 5, 5, 5),
				new Rezession("Super!"),
				oxfordPub);
		
		Tisch tisch1 = saveTisch(new Tisch("Tisch 1"), new Rechnung(), oxfordPub);
		Tisch tisch2 = saveTisch(new Tisch("Tisch 2"), new Rechnung(), oxfordPub);
		Tisch tisch3 = saveTisch(new Tisch("Tisch 3"), new Rechnung(), oxfordPub);
		Tisch tisch4 = saveTisch(new Tisch("Tisch 4"), new Rechnung(), oxfordPub);
		Tisch tisch5 = saveTisch(new Tisch("Tisch 5"), new Rechnung(), oxfordPub);
		Tisch tisch6 = saveTisch(new Tisch("Tisch 6"), new Rechnung(), oxfordPub);
	}

	private void addCafeSimple() throws IOException {
		Restaurant cafeSimple = saveRestaurant(
				new Restaurant("Café Simple", loadTextFromResources("restaurants/cafeSimple"),"CafeSimpleKarlsruhe@gmail.com"),
				"restaurants/cafeSimple");
		
		Standort standortCafeSimple = saveStandort(
				new Standort("Test", "1", 49.1, 8.5, "---"),
				new PLZ(12345, "---"),
				cafeSimple
				);
		
		Speisekarte speisekarteCafeSimple = saveSpeisekarte(
				new Speisekarte(),
				cafeSimple);
		
		Speise chickenBagel = saveSpeise(
				new Speise("Chicken Bagel", "Bagel aus Weißbrot mit frischem Salat, Chicken und Kresse.", 2.5, null, false, false, "Unser Genuss-Schlager aus Amerika: der Chicken Bagel! Mit seinem saftigen, paniertem Hähnchenfleisch und dem knackig-frischen Salat ist er vielmehr als nur 'ne Runde Sache. Kurz gesagt: den musst du unbedingt probieren!"),
				speisekarteCafeSimple,
				"speisen/chickenBagel",
				new ArrayList<Allergen>(Arrays.asList(gluten, ei)));
		
		Speise freshBagel = saveSpeise(
				new Speise("Fresh Bagel", "Bagel aus Vollkornbrot mit frischem Salat, Tomate, Gurke und Frischkäse.", 2.5, null, true, false, "Hast du Lust auf eine Bagatelle? Dann empfehlen wir unseren köstlichen Bagel. Auf Vollkornbrot garnierter Salat, Tomate und Gurke. Dazu etwas Frischkäse als überzeugender Geschmacksträger. Gesund und dennoch überzeugend im Geschmack, er gehört dir"),
				speisekarteCafeSimple,
				"speisen/freshBagel",
				new ArrayList<Allergen>(Arrays.asList(gluten, ei, erdnuesse)));
		
		Speise pommes = saveSpeise(
				new Speise("Pommes", "200g frische Pommes, mit Ketchup und/oder Mayonnaise.", 2, null, true, true, "Ob schlechte oder gute Laune, mit unseren Pommes gehts dir besser. Ob Groß oder Klein, eine Bestellung und die Pommes sind deins. Unsere Pommes sind handgeschnitzt aus regionalen Kartoffeln, also ein absolutes Must-Have"),
				speisekarteCafeSimple,
				"speisen/pommes",
				null);

		Speise donut = saveSpeise(
				new Speise("Donut", "Süßer Donut mit Zucker-Glasur und Füllung.", 2.5, null, true, false, "Der Vorteil von Donuts? Sie schmecken so gut wie 10 Schnitzel und einer Brise Zucker. Mit der geschmacksvollen Füller und einer überzeugenden GLasur gehört der Donut zu unseren Spezialitäten"),
				speisekarteCafeSimple,
				"speisen/donut",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Speise cookie = saveSpeise(
				new Speise("Cookie", "Chocolate Cookie", 1, null, true, false, "Was gibt es denn bei einem Cookie gepaart mit Schokolade schon zu sagen? Wir denken unser Chocolate Cookie spricht definitiv für sich. \"Egal wie voll du bist, ein Cookie geht immer\" (Das Café Simple Team)"),
				speisekarteCafeSimple,
				"speisen/cookie",
				null);
		
		Getraenk espresso = saveGetraenk(
				new Getraenk("Espresso", "Espresso, italienische Röstung", 2, null, true, true, "Cara mia! - Meine Liebe! Was gibt es schöeneres als nach dem Essen einen echten italienischen Espresso zu genießen? Wir sagen es dir: nichts. Also lass es dir schmecken"),
				speisekarteCafeSimple,
				"getraenk/espresso",
				null);
		
		Getraenk cappuccino = saveGetraenk(
				new Getraenk("Cappuccino", "Eine Tasse Cappuccino", 3, null, true, false, "Ein bisschen Schaum muss sein, dann ist die Welt ein Sonnenschein! So gut wie wir uns heute versteht, so soll es ... Cappucino!"),
				speisekarteCafeSimple,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk latte = saveGetraenk(
				new Getraenk("Latte Macchiato", "Viel Milch, wenig Kaffee", 2.5, null, true, false, "Was braucht der Mensch? Ein Kaffe und viel Milch. Da können wir ihnen wärmstens unseren Latte Macchiato empfehlen. Probiere ihn gerne aus"),
				speisekarteCafeSimple,
				"getraenk/latte",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk limeSmoothie = saveGetraenk(
				new Getraenk("Lime Smoothie", "Gesunder Smoothie aus frischen Limetten", 4.5, null, true, true, "Der Lime Smoothie, oder wie wir ihn gerne nennen: unser gesunder Caipirinha! Der absolute Knaller für jeden der gerne in der Smoothie-Blase lebt."),
				speisekarteCafeSimple,
				"getraenk/limeSmoothie",
				null);
		
		Getraenk tee = saveGetraenk(
				new Getraenk("Orangen Tee", "Frischer Tee mit Orangen Geschmak", 1.5, null, true, true, "Tee kann man machen warm, Tee kann man machen kalt - Wir servieren unsere gerne heiß, mit einem süßen Orangengeschmack. Für jeden Teeliebhaber eine wohlgesonnene Abwechslung zu Kamille, Ingewer oder Salbei-Honig. Unser Orangen-Tee schlägt sie alle, probiere es selbst"),
				speisekarteCafeSimple,
				"getraenk/tee",
				null);
		
		speisekarteCafeSimple = updateSpeisekarte(
				speisekarteCafeSimple,
				new ArrayList<Getraenk>(Arrays.asList(espresso, cappuccino, latte, limeSmoothie, tee)), 
				new ArrayList<Speise>(Arrays.asList(chickenBagel, freshBagel, pommes, donut, cookie)));

		Bewertung cafeSimpleBewertung1 = saveBewertung(
				new Bewertung(5, 5, 4, 4, 5),
				new Rezession("Sehr gutes Restaurant, Bagel sind fantastisch!"),
				cafeSimple);
		
		Bewertung cafeSimpleBewertung2 = saveBewertung(
				new Bewertung(4, 5, 5, 5, 3),
				new Rezession("Insgesamt super Restaurant. Nachhaltig und Modern!"),
				cafeSimple);
		
		Bewertung cafeSimpleBewertung3 = saveBewertung(
				new Bewertung(5, 5, 5, 3, 4),
				new Rezession("Gefällt mir sehr gut, bin hier öfter in der Mittagspause und genieße einen Kaffee."),
				cafeSimple);
		
		Bewertung cafeSimpleBewertung4 = saveBewertung(
				new Bewertung(1, 2, 2, 1, 1),
				new Rezession("Versuchen modern zu sein, schaffen es aber nicht! Essen schmeckt nicht gut."),
				cafeSimple);
		
		Bewertung cafeSimpleBewertung5 = saveBewertung(
				new Bewertung(4, 3, 4, 5, 2),
				new Rezession("Gut zum essen, Atmosphäre nicht so toll."),
				cafeSimple);
		
		Tisch tisch1 = saveTisch(new Tisch("Tisch 1"), new Rechnung(), cafeSimple);
		Tisch tisch2 = saveTisch(new Tisch("Tisch 2"), new Rechnung(), cafeSimple);
		Tisch tisch3 = saveTisch(new Tisch("Tisch 3"), new Rechnung(), cafeSimple);
		Tisch tisch4 = saveTisch(new Tisch("Tisch 4"), new Rechnung(), cafeSimple);
		Tisch tisch5 = saveTisch(new Tisch("Tisch 5"), new Rechnung(), cafeSimple);
		Tisch tisch6 = saveTisch(new Tisch("Tisch 6"), new Rechnung(), cafeSimple);
		

	}

}
