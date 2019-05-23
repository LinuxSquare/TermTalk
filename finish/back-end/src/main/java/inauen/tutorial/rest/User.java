package inauen.tutorial.rest;

public class User {

	private String id;
	private String name;
	private String ip;


	// Damit wir immer die selben Attributnamen verwenden, machen wir noch ein paar Statics.
	public static String ATTR_NAME = "name";
	public static String ATTR_IP = "ip";

	public User(String name, String ip)
	{
		this.name = name;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIP() {
		return ip;
	}
	public void setIP(String ip) {
		this.ip = ip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
