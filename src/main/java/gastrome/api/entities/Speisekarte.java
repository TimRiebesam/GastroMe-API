package gastrome.api.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Speisekarte {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
	@JsonBackReference(value = "restaurant-speisekarte")
	private Restaurant restaurant;
	
	@OneToMany(
			mappedBy = "speisekarte",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "speisekarte-speisen")
	private List<Speise> speisen;
	
	@OneToMany(
			mappedBy = "speisekarte",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY)
	@JsonManagedReference(value = "speisekarte-getraenke")
	private List<Getraenk> getraenke;
	
}
