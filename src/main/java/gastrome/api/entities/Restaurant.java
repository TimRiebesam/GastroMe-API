package gastrome.api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//Autor: Tim Riebesam, Tim Bayer
//Diese Klasse beschreibt ein Restaurant. Ein Restaurant befindet sich an einem Standort, besitzt Rezessionen, Feebacks, Tische und eine Speisekarte. Weiter hat ein Restaurant einen Namen, eine Beschreibung, eine E-MailAdresse und ein Titelbild.

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	private String name;
	
	@Lob
	private String beschreibung;
	
	@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	@JsonManagedReference(value = "restaurant-standort")
	private Standort standort;
	
	@OneToMany(
			mappedBy = "restaurant",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "restaurant-rezessionen")
	private List<Rezession> rezessionen = new ArrayList<Rezession>();
	
	@OneToMany(
			mappedBy = "restaurant",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "restaurant-feedbacks")
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	
	@OneToMany(
			mappedBy = "restaurant",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "restaurant-tische")
	private List<Tisch> tische = new ArrayList<Tisch>();
	
	@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	@JsonBackReference(value = "restaurant-speisekarte")
	private Speisekarte speisekarte;
	
	@Lob
	private byte[] bild;
	
	private String email;
	
	public Restaurant(String name, String beschreibung, String email) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.email = email;
	}

	public Restaurant() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Standort getStandort() {
		return standort;
	}

	public void setStandort(Standort standort) {
		this.standort = standort;
	}

	public List<Rezession> getRezessionen() {
		return rezessionen;
	}

	public void addRezession(Rezession rezession) {
		this.rezessionen.add(rezession);
	}

	public Speisekarte getSpeisekarte() {
		return speisekarte;
	}

	public void setSpeisekarte(Speisekarte speisekarte) {
		this.speisekarte = speisekarte;
	}

	public UUID getId() {
		return id;
	}

	public byte[] getBild() {
		return bild;
	}

	public void setBild(byte[] bild) {
		this.bild = bild;
	}

	public List<Tisch> getTische() {
		return tische;
	}

	public void setTische(List<Tisch> tische) {
		this.tische = tische;
	}
	
	public void addTisch(Tisch tisch) {
		this.tische.add(tisch);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
	public void addFeedback(Feedback feedback) {
		this.feedbacks.add(feedback);
	}
	
	
}
