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

//Autor: Tim Riebesam
//Diese Klasse beschreibt einen Standort. Er befindet sich innerhalb eines PLZ-Gebiets und es kann sich ein Restaurant an einem Standort befinden. Außerdem besitzt ein Standort eine starße, eine hausnummer, einen längen- und breitengrad und eine beschreibung.

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
	
	private double laengengrad;
	
	private double breitengrad;
	
	private String beschreibung;
	
	public Standort(String strasse, String hausnummer, double breitengrad, double laengengrad, String beschreibung) {
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

	public double getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(double laengengrad) {
		this.laengengrad = laengengrad;
	}

	public double getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(double breitengrad) {
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
