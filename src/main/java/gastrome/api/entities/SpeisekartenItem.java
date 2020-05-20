package gastrome.api.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SpeisekartenItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	private String name;
	
	private String beschreibung;
	
	@Column(length = 1000)
	private String erlaeuterung;

	private double preis;
	
	@Lob
	private byte[] bild;
	
	private boolean vegie;
	
	private boolean vegan;
	
	public SpeisekartenItem() {
		
	}
	
	public SpeisekartenItem(String name, String beschreibung, double preis, byte[] bild, boolean vegie, boolean vegan, String erlaeuterung) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.bild = bild;
		this.vegie = vegie;
		this.vegan = vegan;
		this.erlaeuterung = erlaeuterung;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public String getErlaeuterung() {
		return erlaeuterung;
	}

	public void setErlaeuterung(String erlaeuterung) {
		this.erlaeuterung = erlaeuterung;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public byte[] getBild() {
		return bild;
	}

	public void setBild(byte[] bild) {
		this.bild = bild;
	}

	public boolean isVegie() {
		return vegie;
	}

	public void setVegie(boolean vegie) {
		this.vegie = vegie;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public UUID getId() {
		return id;
	}
	
}
