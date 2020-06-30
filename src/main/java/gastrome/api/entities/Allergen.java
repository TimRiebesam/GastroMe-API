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
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Autor: Tim Riebesam
// Diese Klasse beschreibt ein Allergen. Allergene können in Essen und Getränken vorhanden sein. Ein Allergen hat einen Namen, eine Bezeichnung und ein Symbol (Bild)

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Allergen {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
            },
            mappedBy = "allergene")
	@JsonBackReference(value = "speisen-allergene")
	private List<Speise> speisen = new ArrayList<Speise>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
            },
            mappedBy = "allergene")
	@JsonBackReference(value = "getraenke-allergene")
	private List<Getraenk> getraenke = new ArrayList<Getraenk>();
	
	private String name;
	
	private String bezeichnung;
	
	@Lob
	private byte[] symbol;
	
	public Allergen() {
		
	}
	
	public Allergen(String name, String bezeichnung, byte[] symbol) {
		this.name = name;
		this.bezeichnung = bezeichnung;
		this.symbol = symbol;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public byte[] getSymbol() {
		return symbol;
	}

	public void setSymbol(byte[] symbol) {
		this.symbol = symbol;
	}

	public UUID getId() {
		return id;
	}
	
}
