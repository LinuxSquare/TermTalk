package inauen.tutorial.rest;

public class Teacher {
	
	private String id;
	private String vorname;
	private String nachname;
	private String fachbereich;
	
	// Damit wir immer die selben Attributnamen verwenden, machen wir noch ein paar Statics.	
	public static String ATTR_VORNAME = "vorname";
	public static String ATTR_NACHNAME = "nachname";
	public static String ATTR_FACHBEREICH = "fachbereich";
	
	public Teacher(String vorname, String nachname,String fachbereich)
	{
		this.vorname = vorname;
		this.nachname = nachname;
		this.fachbereich = fachbereich;
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getFachbereich() {
		return fachbereich;
	}
	public void setFachbereich(String fachbereich) {
		this.fachbereich = fachbereich;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
