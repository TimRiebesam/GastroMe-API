package gastrome.api.entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rezession {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	@JsonBackReference(value = "restaurant-rezessionen")
	private Restaurant restaurant;
	
	@OneToOne(mappedBy = "rezession", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
	@JsonManagedReference(value = "rezession-bewertung")
	private Bewertung bewertung;
	
	private String anmerkung;
	
	public Rezession() {

	}
	
	public Rezession(String anmerkung) {
		this.anmerkung = anmerkung;
	}
	
	public Rezession(String anmerkung, Bewertung bewertung) {
		this.anmerkung = anmerkung;
		this.bewertung = bewertung;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Bewertung getBewertung() {
		return bewertung;
	}

	public void setBewertung(Bewertung bewertung) {
		this.bewertung = bewertung;
	}

	public String getAnmerkung() {
		return anmerkung;
	}

	public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}

	public UUID getId() {
		return id;
	}
	
}
