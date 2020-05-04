package gastrome.api.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PLZ {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@OneToMany(
			mappedBy = "plz",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonBackReference(value = "standorte-plz")
	private List<Standort> standorte;
	
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
