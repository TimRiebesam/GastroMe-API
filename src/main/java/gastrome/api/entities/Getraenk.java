package gastrome.api.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Getraenk extends SpeisekartenItem{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speisekarteId")
	@JsonBackReference(value = "speisekarte-getraenke")
	private Speisekarte speisekarte;
	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE,
	    })
	@JoinTable(name = "getraenk_allergen",
	        joinColumns = @JoinColumn(name = "getraenk_id"),
	        inverseJoinColumns = @JoinColumn(name = "allergene_id")
	    )
	@JsonManagedReference(value = "getraenke-allergene")
	private List<Allergen> allergene;

	public Getraenk() {
		
	}
	
	public Getraenk(String name, String beschreibung, double preis, byte[] bild, boolean vegie, boolean vegan) {
		super(name, beschreibung, preis, bild, vegie, vegan);
	}
	
	public Speisekarte getSpeisekarte() {
		return speisekarte;
	}

	public void setSpeisekarte(Speisekarte speisekarte) {
		this.speisekarte = speisekarte;
	}

	public List<Allergen> getAllergene() {
		return allergene;
	}

	public void setAllergene(List<Allergen> allergene) {
		this.allergene = allergene;
	}

	public UUID getId() {
		return id;
	}
	
}
