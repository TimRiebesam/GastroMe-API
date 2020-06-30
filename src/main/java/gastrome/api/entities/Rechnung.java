package gastrome.api.entities;

import java.util.Date;
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
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//Autor: Tim Riebesam
//Diese Klasse beschreibt eine Rechnung. Eine Rechnung ist einem Tisch zugeordnet. Sie besitzt Speisen und GetraenkOrders. Außerdem ist sie mit einem Zeitstempel und einem Flag ob die Rechnung bezahlt ist versehen.

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
	
	@OneToMany(
			mappedBy = "rechnung",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "rechnung-getraenkOrder")
	private List<GetraenkOrder> getraenkOrders = new ArrayList<GetraenkOrder>();
	
	private Date timestamp;
	
	private boolean billPayed;

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

	public UUID getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date date) {
		this.timestamp = date;
	}

	public boolean isBillPayed() {
		return billPayed;
	}

	public void setBillPayed(boolean billPayed) {
		this.billPayed = billPayed;
	}

	public List<GetraenkOrder> getGetraenkOrders() {
		return getraenkOrders;
	}

	public void setGetraenkOrders(List<GetraenkOrder> getraenkOrders) {
		this.getraenkOrders = getraenkOrders;
	}
	
	public void addGetraenkOrder(GetraenkOrder getraenkOrder) {
		this.getraenkOrders.add(getraenkOrder);
	}
	
}
