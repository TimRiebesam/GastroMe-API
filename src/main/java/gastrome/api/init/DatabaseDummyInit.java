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
import gastrome.api.entities.Restaurant;
import gastrome.api.entities.Rezession;
import gastrome.api.entities.Speise;
import gastrome.api.entities.Speisekarte;
import gastrome.api.entities.Standort;
import gastrome.api.repositories.AllergenRepository;
import gastrome.api.repositories.BewertungRepository;
import gastrome.api.repositories.GetraenkRepository;
import gastrome.api.repositories.PLZRepository;
import gastrome.api.repositories.RestaurantRepository;
import gastrome.api.repositories.RezessionRepository;
import gastrome.api.repositories.SpeiseRepository;
import gastrome.api.repositories.SpeisekarteRepository;
import gastrome.api.repositories.StandortRepository;
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
	

	//@PostConstruct
	public void loadDummyDataIntoDatabase() throws IOException {
		clearDatabase();
		addAllergene();
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
		
		Restaurant woodie = new Restaurant("Woodie", "Café in Holzoptik in der Nordstadt,bekannt für Kaffee, Kakao und\r\nSüßes Gebäck...");
		woodie.setBild(loadImageFromResources("restaurants/oxford"));
		woodie = restaurantRepository.save(woodie);
		
		Restaurant burgerParadise = new Restaurant("Burger Paradise", "Burger Restaurant in der Innenstadt, bekannt für Burger, Fritten und Eistee...");
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

	private void addCafePalaver() throws IOException {
		Restaurant cafePalaver = saveRestaurant(
				new Restaurant("café palaver", loadTextFromResources("restaurants/palaver")),
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
				new Speise("Mini", "helles Brötchen, Butter, Fruchtaufstrich oder Honig", 2.9, null, true, false),
				speisekarteCafePalaver,
				"speisen/palaver_mini",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten)));
		
		Speise petit = saveSpeise(
				new Speise("Petit Déjeuner", "Croissants, Butter und Fruchtaufstrich oder Honig", 3.2, null, true, false),
				speisekarteCafePalaver,
				"speisen/palaver_petit",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten)));
		
		Speise breakfast = saveSpeise(
				new Speise("Breakfast", "2 Spiegeleier mit Frühstücksspeck, Orangenmarmelade, Butter & Brötchen", 7.9, null, false, false),
				speisekarteCafePalaver,
				"speisen/palaver_breakfast",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten, ei)));
		
		Speise bauarbeiterin = saveSpeise(
				new Speise("Bauarbeiterin", "Putensalami, Schinken, Käse, Ei, Butter, 1 Scheibe Brot & 2 Brötchen", 8.5, null, false, false),
				speisekarteCafePalaver,
				"speisen/palaver_bauarbeiterin",
				new ArrayList<Allergen>(Arrays.asList(milch, gluten, ei)));

		Speise muesli = saveSpeise(
				new Speise("Müsli", "mit Joghurt, Nüssen und Früchten", 5.2, null, true, true),
				speisekarteCafePalaver,
				"speisen/palaver_muesli",
				new ArrayList<Allergen>(Arrays.asList(milch, erdnuesse)));
		
		Speise obstsalat = saveSpeise(
				new Speise("Obstsalat", "nach Saison", 3.2, null, true, true),
				speisekarteCafePalaver,
				"speisen/palaver_obst",
				null);
		
		Getraenk espresso = saveGetraenk(
				new Getraenk("Espresso", "von der Karlsruher Rösterei Tostino", 2, null, true, true),
				speisekarteCafePalaver,
				"getraenk/espresso",
				null);
		
		Getraenk cappuccino = saveGetraenk(
				new Getraenk("Cappuccino", "von der Karlsruher Rösterei Tostino", 2.9, null, true, false),
				speisekarteCafePalaver,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk latte = saveGetraenk(
				new Getraenk("Latte Macchiato", "von der Karlsruher Rösterei Tostino", 3.5, null, true, false),
				speisekarteCafePalaver,
				"getraenk/latte",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk orangensaft = saveGetraenk(
				new Getraenk("Orangensaft 0,4", "ohen Fruchtfleisch, von Jacoby", 5.8, null, true, true),
				speisekarteCafePalaver,
				"getraenk/orangensaft",
				null);
		
		Getraenk tee = saveGetraenk(
				new Getraenk("Orangen Tee", "von Lebensbaum oder Yogitea", 2.5, null, true, true),
				speisekarteCafePalaver,
				"getraenk/tee",
				null);
		
		Getraenk sekt = saveGetraenk(
				new Getraenk("Sekt", "Hausmarke 0,1 l", 3.2, null, true, true),
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
	}
	
	private void addCafePerlbohne() throws IOException {
		Restaurant cafePerlbohne = saveRestaurant(
				new Restaurant("Perlbohne", loadTextFromResources("restaurants/perlbohne")),
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
				new Getraenk("Espresso 1 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 1.8, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/espresso",
				null);
		
		Getraenk espresso2 = saveGetraenk(
				new Getraenk("Espresso 2 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.5, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/espresso",
				null);
		
		Getraenk espressoMachhiato1 = saveGetraenk(
				new Getraenk("Espresso Macchiato 1 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.0, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/espresso_mach",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk espressoMachhiato2 = saveGetraenk(
				new Getraenk("Espresso Macchiato 2 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.7, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/espresso_mach",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk cappuccino = saveGetraenk(
				new Getraenk("Cappucchino 1 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 2.6, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk flatWhite = saveGetraenk(
				new Getraenk("Flat White 2 Shot", "Hausröstung oder monatlich wechselnde Röstung. Lass dich von unserer Baristi beraten!", 3.2, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk chocolate = saveGetraenk(
				new Getraenk("Heiße Schokolade", "", 2.9, null, true, true),
				speisekarteCafePerlbohne,
				"getraenk/hotChocolate",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk tee = saveGetraenk(
				new Getraenk("Teebeutel", "Schlürfel diverse Sorten", 1.9, null, true, true),
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
	}

	private void addCafeSimple() throws IOException {
		Restaurant cafeSimple = saveRestaurant(
				new Restaurant("Café Simple", loadTextFromResources("restaurants/cafeSimple")),
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
				new Speise("Chicken Bagel", "Bagel aus Weißbrot mit frischem Salat, Chicken und Kresse.", 2.5, null, false, false),
				speisekarteCafeSimple,
				"speisen/chickenBagel",
				new ArrayList<Allergen>(Arrays.asList(gluten, ei)));
		
		Speise freshBagel = saveSpeise(
				new Speise("Fresh Bagel", "Bagel aus Vollkornbrot mit frischem Salat, Tomate, Gurke und Frischkäse.", 2.5, null, true, false),
				speisekarteCafeSimple,
				"speisen/freshBagel",
				new ArrayList<Allergen>(Arrays.asList(gluten, ei, erdnuesse)));
		
		Speise pommes = saveSpeise(
				new Speise("Pommes", "200g frische Pommes, mit Ketchup und/oder Mayonnaise.", 2, null, true, true),
				speisekarteCafeSimple,
				"speisen/pommes",
				null);

		Speise donut = saveSpeise(
				new Speise("Donut", "Süßer Donut mit Zucker-Glasur und Füllung.", 2.5, null, true, false),
				speisekarteCafeSimple,
				"speisen/donut",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Speise cookie = saveSpeise(
				new Speise("Cookie", "Chocolate Cookie", 1, null, true, false),
				speisekarteCafeSimple,
				"speisen/cookie",
				null);
		
		Getraenk espresso = saveGetraenk(
				new Getraenk("Espresso", "Espresso, italienische Röstung", 2, null, true, true),
				speisekarteCafeSimple,
				"getraenk/espresso",
				null);
		
		Getraenk cappuccino = saveGetraenk(
				new Getraenk("Cappuccino", "Eine Tasse Cappuccino", 3, null, true, false),
				speisekarteCafeSimple,
				"getraenk/cappuccino",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk latte = saveGetraenk(
				new Getraenk("Latte Macchiato", "Viel Milch, wenig Kaffee", 2.5, null, true, false),
				speisekarteCafeSimple,
				"getraenk/latte",
				new ArrayList<Allergen>(Arrays.asList(milch)));
		
		Getraenk limeSmoothie = saveGetraenk(
				new Getraenk("Lime Smoothie", "Gesunder Smoothie aus frischen Limetten", 4.5, null, true, true),
				speisekarteCafeSimple,
				"getraenk/limeSmoothie",
				null);
		
		Getraenk tee = saveGetraenk(
				new Getraenk("Orangen Tee", "Frischer Tee mit Orangen Geschmak", 1.5, null, true, true),
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
	}

}
