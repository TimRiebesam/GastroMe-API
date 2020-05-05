package gastrome.api.entities;

import java.util.UUID;

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
public class Standort {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
	@JsonBackReference(value = "restaurant-standort")
	private Restaurant restaurant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plz_id")
	@JsonManagedReference(value = "standorte-plz")
	private PLZ plz;
	
	private String strasse;
	
	private String hausnummer;
	
	private String laengengrad;
	
	private String breitengrad;
	
	private String beschreibung;
	
	public Standort(String strasse, String hausnummer, String laengengrad, String breitengrad, String beschreibung) {
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.laengengrad = laengengrad;
		this.breitengrad = breitengrad;
		this.beschreibung = beschreibung;
	}
	
	public Standort() {
		
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public PLZ getPlz() {
		return plz;
	}

	public void setPlz(PLZ plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(String laengengrad) {
		this.laengengrad = laengengrad;
	}

	public String getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(String breitengrad) {
		this.breitengrad = breitengrad;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public UUID getId() {
		return id;
	}
	
}
