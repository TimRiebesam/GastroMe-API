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

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gast {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Type(type="uuid-char")
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tisch_id")
	@JsonBackReference(value = "tisch-gaeste")
	private Tisch tisch;
	
	public Tisch getTisch() {
		return tisch;
	}

	public void setTisch(Tisch tisch) {
		this.tisch = tisch;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
