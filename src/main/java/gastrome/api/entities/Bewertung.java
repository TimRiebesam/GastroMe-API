package gastrome.api.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Bewertung {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezession_id")
	@JsonManagedReference(value = "rezession-bewertung")
	private Rezession rezession;
	
	private int essen;
	
	private int atmosphaere;
	
	private int service;
	
	private int preise;
	
	private int sonderwünsche;
	
	public Bewertung() {

	}
	
	public Bewertung(int essen, int atmosphaere, int service, int preise, int sonderwünsche) {
		this.essen = essen;
		this.atmosphaere = atmosphaere;
		this.service = service;
		this.preise = preise;
		this.sonderwünsche = sonderwünsche;
	}

	public Rezession getRezession() {
		return rezession;
	}

	public void setRezession(Rezession rezession) {
		this.rezession = rezession;
	}

	public int getEssen() {
		return essen;
	}

	public void setEssen(int essen) {
		this.essen = essen;
	}

	public int getAtmosphaere() {
		return atmosphaere;
	}

	public void setAtmosphaere(int atmosphaere) {
		this.atmosphaere = atmosphaere;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getPreise() {
		return preise;
	}

	public void setPreise(int preise) {
		this.preise = preise;
	}

	public int getSonderwünsche() {
		return sonderwünsche;
	}

	public void setSonderwünsche(int sonderwünsche) {
		this.sonderwünsche = sonderwünsche;
	}

	public UUID getId() {
		return id;
	}
	
}
