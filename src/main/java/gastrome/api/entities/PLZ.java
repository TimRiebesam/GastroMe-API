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
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Autor: Tim Riebesam
//Diese Klasse beschreibt eine Postleitzahl. Innerhalb einer Postleitzahl können sich viele Standorte befinden. Der Wert der Postleitzahl ist in Form des Attributs plz gespeichert. Außerdem kann die stadt angegeben werden.

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PLZ {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@OneToMany(
			mappedBy = "plz",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonBackReference(value = "standorte-plz")
	private List<Standort> standorte = new ArrayList<Standort>();
	
	private int plz;
	
	private String stadt;
	
	public PLZ(int plz, String stadt) {
		this.plz = plz;
		this.stadt = stadt;
	}

	public PLZ() {
		
	}

	public List<Standort> getStandorte() {
		return standorte;
	}

	public void addStandort(Standort standort) {
		this.standorte.add(standort);
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public UUID getId() {
		return id;
	}
	
}
