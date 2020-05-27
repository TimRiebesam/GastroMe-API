package gastrome.api.entities;

import java.sql.Date;
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
public class Rechnung {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tisch_id")
	@JsonBackReference(value = "tisch-rechnungen")
	private Tisch tisch;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
            })
	@JoinTable(name = "rechnung_speise",
	        joinColumns = @JoinColumn(name = "rechnung_id", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name = "speise_id", referencedColumnName="id")
	    )
	@JsonManagedReference(value = "rechnungen-speisen")
	private List<Speise> speisen = new ArrayList<Speise>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
            })
	@JoinTable(name = "rechnung_getraenk",
	        joinColumns = @JoinColumn(name = "rechnung_id", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name = "getraenk_id", referencedColumnName="id")
	    )
	@JsonManagedReference(value = "rechnungen-getraenke")
	private List<Getraenk> getraenke = new ArrayList<Getraenk>();
	
	private Date timestamp;

	public Tisch getTisch() {
		return tisch;
	}

	public void setTisch(Tisch tisch) {
		this.tisch = tisch;
	}

	public List<Speise> getSpeisen() {
		return speisen;
	}

	public void setSpeisen(List<Speise> speisen) {
		this.speisen = speisen;
	}
	
	public void addSpeise(Speise speise) {
		this.speisen.add(speise);
	}

	public List<Getraenk> getGetraenke() {
		return getraenke;
	}

	public void setGetraenke(List<Getraenk> getraenke) {
		this.getraenke = getraenke;
	}
	
	public void addGetraenk(Getraenk getraenk) {
		this.getraenke.add(getraenk);
	}

	public UUID getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
