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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Getraenk extends SpeisekartenItem{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speisekarte_id")
	@JsonBackReference(value = "speisekarte-getraenke")
	private Speisekarte speisekarte;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
            })
	@JoinTable(name = "getraenk_allergen",
	        joinColumns = @JoinColumn(name = "getraenk_id"),
	        inverseJoinColumns = @JoinColumn(name = "allergene_id")
	    )
	@JsonManagedReference(value = "getraenke-allergene")
	private List<Allergen> allergene = new ArrayList<Allergen>();

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

	public void addAllergen(Allergen allergen) {
		this.allergene.add(allergen);
	}

	public UUID getId() {
		return id;
	}

	public void setAllergene(List<Allergen> allergene) {
		this.allergene = allergene;
	}
	
}
