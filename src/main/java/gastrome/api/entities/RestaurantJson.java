package gastrome.api.entities;

public class RestaurantJson {

	private String name;
	private String beschreibung;
	private String email;
	private String stadt;
	private String plz;
	private String strasse;
	private int hausnummer;
	private String bild;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPlz() {
		return plz;
	}
	
	public void setPlz(String plz) {
		this.plz = plz;
	}
	
	public String getStrasse() {
		return strasse;
	}
	
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public int getHausnummer() {
		return hausnummer;
	}
	
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	public String getBild() {
		return bild;
	}
	
	public void setBild(String bild) {
		this.bild = bild;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	
}
