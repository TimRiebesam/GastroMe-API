package gastrome.api.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Autor: Tim Bayer
//Diese Klasse beschreibt ein Feedback. Feedback kann direkt vom Gast über die App abgegeben werden. Ein Feedback gehört immer zu einem Restaurant und besitzt eine kategorie und eine Anmerkung

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	String kategorie;
	
	String anmerkung;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	@JsonBackReference(value = "restaurant-feedbacks")
	private Restaurant restaurant;

	public Feedback() {
		
	}
	
	public Feedback(String kategorie, String anmerkung, Restaurant restaurant) {
		super();
		this.kategorie = kategorie;
		this.anmerkung = anmerkung;
		this.restaurant = restaurant;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getAnmerkung() {
		return anmerkung;
	}

	public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
