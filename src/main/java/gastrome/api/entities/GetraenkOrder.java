package gastrome.api.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GetraenkOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rechnung_id")
	@JsonBackReference(value = "rechnung-getraenkOrder")
	private Rechnung rechnung;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "getraenk_id")
	@JsonManagedReference(value = "getraenk-getraenkOrder")
	private Getraenk getraenk;
	
	private boolean ausgeliefert;
	
	public GetraenkOrder() {
		
	}
	
	public GetraenkOrder(Rechnung rechnung, Getraenk getraenk) {
		this.rechnung = rechnung;
		this.getraenk = getraenk;
	}

	public Rechnung getRechnung() {
		return rechnung;
	}

	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}

	public Getraenk getGetraenk() {
		return getraenk;
	}

	public void setGetraenk(Getraenk getraenk) {
		this.getraenk = getraenk;
	}

	public boolean isAusgeliefert() {
		return ausgeliefert;
	}

	public void setAusgeliefert(boolean ausgeliefert) {
		this.ausgeliefert = ausgeliefert;
	}

	public UUID getId() {
		return id;
	}
	
}
