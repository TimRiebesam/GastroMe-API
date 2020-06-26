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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tisch {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	private String nummer;
	
	private String beschreibung;
	
	private boolean kellnerGerufen;
	
	@OneToMany(
			mappedBy = "tisch",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "tisch-gaeste")
	private List<Gast> gaeste = new ArrayList<Gast>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	@JsonBackReference(value = "restaurant-tische")
	private Restaurant restaurant;
	
	@OneToMany(
			mappedBy = "tisch",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "tisch-rechnungen")
	private List<Rechnung> rechnungen = new ArrayList<Rechnung>();
	
	public Tisch() {
		
	}
	
	public Tisch(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public boolean isKellnerGerufen() {
		return kellnerGerufen;
	}

	public void setKellnerGerufen(boolean kellnerGerufen) {
		this.kellnerGerufen = kellnerGerufen;
	}

	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/*public boolean isBesetzt() {
		return besetzt;
	}

	public void setBesetzt(boolean besetzt) {
		this.besetzt = besetzt;
	}*/

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Rechnung> getRechnungen() {
		return rechnungen;
	}

	public void setRechnungen(List<Rechnung> rechnungen) {
		this.rechnungen = rechnungen;
	}
	
	public void addRechnung(Rechnung rechnung) {
		this.rechnungen.add(rechnung);
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public List<Gast> getGaeste() {
		return gaeste;
	}

	public void setGaeste(List<Gast> gaeste) {
		this.gaeste = gaeste;
	}
	
	public void addGast(Gast gast) {
		this.gaeste.add(gast);
	}
	
	public void clearGaeste() {
		this.gaeste.clear();
	}
	
}
