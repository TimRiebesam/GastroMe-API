package gastrome.api.init;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

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

	@PostConstruct
	public void loadDummyDataIntoDatabase() throws IOException {
		clearDatabase();
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

	private void fillDatabaseWithInitData() throws IOException {
		addCafeSimple();

		Restaurant greenGarden = new Restaurant("Green Garden", "Idyllisches Restaurant im Bahnhof, bekannt für vegetarisches Essen und gesunde Smoothies...");
		greenGarden = restaurantRepository.save(greenGarden);

		Restaurant woodie = new Restaurant("Woodie", "Café in Holzoptik in der Nordstadt,bekannt für Kaffee, Kakao und\r\nSüßes Gebäck...");
		woodie = restaurantRepository.save(woodie);

		Restaurant burgerParadise = new Restaurant("Burger Paradise", "Burger Restaurant in der Innenstadt, bekannt für Burger, Fritten und Eistee...");
		burgerParadise = restaurantRepository.save(burgerParadise);
	}

	private void addCafeSimple() throws IOException {
		Restaurant cafeSimple = new Restaurant("Café Simple", "Kleines Café in der Südstadt, bekannt für guten Kaffee und leckere Snacks. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore...");
		cafeSimple = restaurantRepository.save(cafeSimple);

		Standort standortCafeSimple = new Standort("Teststrasse", "1", "...", "...", "Ums Eck rechts");
		PLZ plzCafeSimple = plzRepository.save(new PLZ(76137, "Karlsruhe"));
		standortCafeSimple.setPlz(plzCafeSimple);
		standortCafeSimple.setRestaurant(cafeSimple);
		standortCafeSimple = standortRepository.save(standortCafeSimple);

		Speisekarte speisekarteCafeSimple = new Speisekarte();
		speisekarteCafeSimple.setRestaurant(cafeSimple);
		speisekarteCafeSimple = speisekarteRepository.save(speisekarteCafeSimple);

		Allergen gluten = allergenRepository.save(new Allergen("Gluten", "", null));
		Allergen ei = allergenRepository.save(new Allergen("Ei", "", null));
		Allergen nuss = allergenRepository.save(new Allergen("Nuss", "", null));
		Allergen milch = allergenRepository.save(new Allergen("Milch", "", null));
		Allergen kartoffel = allergenRepository.save(new Allergen("kartoffel", "", null));

		Speise chickenBagel = new Speise("Chicken Bagel", "Bagel aus Weißbrot mit frischem Salat, Chicken und Kresse.", 2.5, null, false, false);
		chickenBagel.setSpeisekarte(speisekarteCafeSimple);
		chickenBagel = speiseRepository.save(chickenBagel);

		chickenBagel.addAllergen(gluten);
		chickenBagel.addAllergen(ei);
		chickenBagel = speiseRepository.save(chickenBagel);
		
		Resource resourceChickenBagel = new ClassPathResource("static/img/chickenBagel.jpg");
		InputStream isChickenBagel = resourceChickenBagel.getInputStream();
		if(resourceChickenBagel.exists()) {
			byte[] chickenBagelImageCompressedAsBytes = imageService.compressJpgImageReturnAsByteArray(isChickenBagel);
			chickenBagel.setBild(chickenBagelImageCompressedAsBytes);
			chickenBagel = speiseRepository.save(chickenBagel);
		}

		Speise freshBagel = new Speise("Fresh Bagel", "Bagel aus Vollkornbrot mit frischem Salat, Tomate, Gurke und Frischkäse.", 2.5, null, true, false);
		freshBagel.setSpeisekarte(speisekarteCafeSimple);
		freshBagel = speiseRepository.save(freshBagel);

		freshBagel.addAllergen(gluten);
		freshBagel.addAllergen(ei);
		freshBagel.addAllergen(nuss);
		freshBagel = speiseRepository.save(freshBagel);

		Resource resourceFreshBagel = new ClassPathResource("static/img/freshBagel.jpg");
		InputStream isFreshBagel = resourceFreshBagel.getInputStream();
		if(resourceFreshBagel.exists()) {
			byte[] freshBagelImageCompressedAsBytes = imageService.compressJpgImageReturnAsByteArray(isFreshBagel);
			freshBagel.setBild(freshBagelImageCompressedAsBytes);
			freshBagel = speiseRepository.save(freshBagel);
		}

		Speise pommes = new Speise("Pommes", "200g frische Pommes, mit Ketchup und/oder Mayonnaise.", 2.2, null, true, true);
		pommes.setSpeisekarte(speisekarteCafeSimple);
		pommes = speiseRepository.save(pommes);

		pommes.addAllergen(kartoffel);
		pommes = speiseRepository.save(pommes);
		
		Resource resourcePommes = new ClassPathResource("static/img/pommes.jpg");
		InputStream isPommes = resourcePommes.getInputStream();
		if(resourcePommes.exists()) {
			byte[] pommesImageCompressedAsBytes = imageService.compressJpgImageReturnAsByteArray(isPommes);
			pommes.setBild(pommesImageCompressedAsBytes);
			pommes = speiseRepository.save(pommes);
		}

		Speise donut = new Speise("Donut", "Süßer Donut mit Zucker-Glasur und Füllung.", 1.5, null, true, false);
		donut.setSpeisekarte(speisekarteCafeSimple);
		donut = speiseRepository.save(donut);

		donut.addAllergen(milch);
		donut = speiseRepository.save(donut);
		
		Resource resourceDonut = new ClassPathResource("static/img/donut.jpg");
		InputStream isDonut = resourceDonut.getInputStream();
		if(resourceDonut.exists()) {
			byte[] donutImageCompressedAsBytes = imageService.compressJpgImageReturnAsByteArray(isDonut);
			donut.setBild(donutImageCompressedAsBytes);
			donut = speiseRepository.save(donut);
		}

		Getraenk espresso = getraenkRepository.save(new Getraenk("Espresso", "Espresso, italienische Röstung", 2, null, true, true));
		espresso.setSpeisekarte(speisekarteCafeSimple);
		espresso = getraenkRepository.save(espresso);

		Getraenk cappuccino = new Getraenk("Cappuccino", "Eine Tasse Cappuccino", 3, null, true, false);
		cappuccino.setSpeisekarte(speisekarteCafeSimple);
		cappuccino = getraenkRepository.save(cappuccino);

		cappuccino.addAllergen(milch);
		cappuccino = getraenkRepository.save(cappuccino);

		Getraenk latte = new Getraenk("Latte Macchiato", "Viel Milch, wenig Kaffee", 2.5, null, true, false);
		latte.setSpeisekarte(speisekarteCafeSimple);
		latte = getraenkRepository.save(latte);

		latte.addAllergen(milch);
		latte = getraenkRepository.save(latte);

		Getraenk limeSmoothie = getraenkRepository.save(new Getraenk("Lime Smoothie", "Gesunder Smoothie aus frischen Limetten", 4.5, null, true, true));
		limeSmoothie.setSpeisekarte(speisekarteCafeSimple);
		limeSmoothie = getraenkRepository.save(limeSmoothie);

		speisekarteCafeSimple.addGetraenk(limeSmoothie);
		speisekarteCafeSimple.addGetraenk(latte);
		speisekarteCafeSimple.addGetraenk(cappuccino);
		speisekarteCafeSimple.addGetraenk(espresso);
		speisekarteCafeSimple.addSpeise(pommes);
		speisekarteCafeSimple.addSpeise(donut);
		speisekarteCafeSimple.addSpeise(freshBagel);
		speisekarteCafeSimple.addSpeise(chickenBagel);

		cafeSimple.setSpeisekarte(speisekarteCafeSimple);
		cafeSimple = restaurantRepository.save(cafeSimple);

		Bewertung cafeSimpleBewertung1 = bewertungRepository.save(new Bewertung(5, 5, 4, 4, 5));
		Bewertung cafeSimpleBewertung2 = bewertungRepository.save(new Bewertung(4, 5, 5, 5, 3));
		Bewertung cafeSimpleBewertung3 = bewertungRepository.save(new Bewertung(5, 5, 5, 3, 4));
		Bewertung cafeSimpleBewertung4 = bewertungRepository.save(new Bewertung(1, 2, 2, 1, 1));
		Bewertung cafeSimpleBewertung5 = bewertungRepository.save(new Bewertung(4, 3, 4, 5, 2));

		Rezession cafeSimpleRezession1 = rezessionRepository.save(new Rezession("Sehr gutes Restaurant, Bagel sind fantastisch!"));
		Rezession cafeSimpleRezession2 = rezessionRepository.save(new Rezession("Insgesamt super Restaurant. Nachhaltig und Modern!"));
		Rezession cafeSimpleRezession3 = rezessionRepository.save(new Rezession("Gefällt mir sehr gut, bin hier öfter in der Mittagspause und genieße einen Kaffee."));
		Rezession cafeSimpleRezession4 = rezessionRepository.save(new Rezession("Versuchen modern zu sein, schaffen es aber nicht! Essen schmeckt nicht gut."));
		Rezession cafeSimpleRezession5 = rezessionRepository.save(new Rezession("Gut zum essen, Atmosphäre nicht so toll."));

		cafeSimpleBewertung1.setRezession(cafeSimpleRezession1);
		cafeSimpleBewertung1 = bewertungRepository.save(cafeSimpleBewertung1);

		cafeSimpleBewertung2.setRezession(cafeSimpleRezession2);
		cafeSimpleBewertung2 = bewertungRepository.save(cafeSimpleBewertung2);

		cafeSimpleBewertung3.setRezession(cafeSimpleRezession3);
		cafeSimpleBewertung3 = bewertungRepository.save(cafeSimpleBewertung3);

		cafeSimpleBewertung4.setRezession(cafeSimpleRezession4);
		cafeSimpleBewertung4 = bewertungRepository.save(cafeSimpleBewertung4);

		cafeSimpleBewertung5.setRezession(cafeSimpleRezession5);
		cafeSimpleBewertung5 = bewertungRepository.save(cafeSimpleBewertung5);

		cafeSimpleRezession1.setRestaurant(cafeSimple);
		cafeSimpleRezession1 = rezessionRepository.save(cafeSimpleRezession1);

		cafeSimpleRezession2.setRestaurant(cafeSimple);
		cafeSimpleRezession2 = rezessionRepository.save(cafeSimpleRezession2);

		cafeSimpleRezession3.setRestaurant(cafeSimple);
		cafeSimpleRezession3 = rezessionRepository.save(cafeSimpleRezession3);

		cafeSimpleRezession4.setRestaurant(cafeSimple);
		cafeSimpleRezession4 = rezessionRepository.save(cafeSimpleRezession4);

		cafeSimpleRezession5.setRestaurant(cafeSimple);
		cafeSimpleRezession5 = rezessionRepository.save(cafeSimpleRezession5);
	}

}
