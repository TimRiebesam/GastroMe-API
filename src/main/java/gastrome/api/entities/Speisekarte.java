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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//Autor: Tim Riebesam
//Diese Klasse beschreibt eine Speisekarte. Eine Speisekarte ist Teil eines Restaurant. Es besitzt Speisen und Getränke.

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speisekarte {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
	@JsonBackReference(value = "restaurant-speisekarte")
	private Restaurant restaurant;
	
	@OneToMany(
			mappedBy = "speisekarte",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "speisekarte-speisen")
	private List<Speise> speisen = new ArrayList<Speise>();
	
	@OneToMany(
			mappedBy = "speisekarte",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "speisekarte-getraenke")
	private List<Getraenk> getraenke = new ArrayList<Getraenk>();

	public Speisekarte() {
		
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Speise> getSpeisen() {
		return speisen;
	}

	public void addSpeise(Speise speise) {
		this.speisen.add(speise);
	}

	public List<Getraenk> getGetraenke() {
		return getraenke;
	}

	public void addGetraenk(Getraenk getraenk) {
		this.getraenke.add(getraenk);
	}

	public UUID getId() {
		return id;
	}

	public void setSpeisen(ArrayList<Speise> speisen) {
		this.speisen = speisen;
	}

	public void setGetraenke(ArrayList<Getraenk> getraenke) {
		this.getraenke = getraenke;
	}
	
}
