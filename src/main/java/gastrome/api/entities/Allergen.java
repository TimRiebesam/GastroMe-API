package gastrome.api.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Allergen {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@ManyToMany(mappedBy = "allergene")
	@JsonBackReference(value = "speisen-allergene")
	private List<Speise> speisen;
	
	@ManyToMany(mappedBy = "allergene")
	@JsonBackReference(value = "getraenke-allergene")
	private List<Getraenk> getraenke;
	
	private String name;
	
	private String bezeichnung;
	
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

	public void setSpeisen(List<Speise> speisen) {
		this.speisen = speisen;
	}

	public List<Getraenk> getGetraenke() {
		return getraenke;
	}

	public void setGetraenke(List<Getraenk> getraenke) {
		this.getraenke = getraenke;
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
