package inauen.tutorial.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author chris
 * Diese Klasse soll für die gesamte API vorhanden sein, deshalb "ApplikationScoped"
 */

@ApplicationScoped
public class UserManager {

	/**
	 * Zugriffe sollen parallel stattfinden dürfen
	 */
	private ConcurrentMap<String, User> inMemStore = new ConcurrentHashMap<>();

	/**
	 * Brauchen wir um ID zu generieren
	 */
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	private AtomicInteger bookIdGenerator = new AtomicInteger(0);


	/**
	 * Konstruktor
	 */
	public UserManager()
	{
		// Dummy Daten
		String id1 = getNextId();
		User t1 = new User("Max", "192.168.1.3");
		t1.setId(id1);

		String id2 = getNextId();
		User t2 = new User("Fritz", "192.168.1.4");
		t2.setId(id2);

		String id3 = getNextId();
		User t3 = new User("Moritz", "192.168.1.5");
		t3.setId(id3);

		String id4 = getNextId();
		User t4 = new User("Jeff", "192.168.1.6");
		t4.setId(id4);


		inMemStore.put(id1, t1);
		inMemStore.put(id2, t2);
		inMemStore.put(id3, t3);
		inMemStore.put(id4, t4);
		System.out.println("Hello World");
		}


	/**
	 * Fügt der HashMap einen neuen User hinzu
	 *
	 * @param user
	 * @return ID des Users
	 */
	public String add(User user)
	{
		String id = getNextId();
		user.setId(id);
		inMemStore.put(id, user);
		return id;
	}

	/**
	 *
	 * @param id - des Users
	 * @return User-Objekt
	 */
	public User get(String id)
	{
		return inMemStore.get(id);
	}

	/**
	 * Gibt eine List aller Users zurück
	 * @return List<User>
	 */
	public List<User> getAll()
	{
		List<User> users = new ArrayList<>();
		users.addAll(inMemStore.values());
		return users;
	}

	/**
	 * Loescht User aus Datenbank
	 * @param id
	 * @return Geloeschtes Objekt oder null
	 */
	public User remove(String id)
	{
		if (inMemStore.containsKey(id))
		{
			User user = inMemStore.get(id);
			inMemStore.remove(id);
			return user;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Aendert einen User in seinen Attributen
	 *
	 * @param id
	 * @param pnamen
	 * @param pip
	 * @return Userobjekt oder null
	 */
	public User merge(String id, String pnamen, String pip)
	{
		User tochange = inMemStore.get(id);

		if (tochange != null)
		{
			tochange.setIP(pip);
			tochange.setName(pnamen);

			return tochange;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Private Hilfsmethode um eindeutige Keys für die Map zu generieren
	 * @return String
	 */
	private String getNextId()
	{
		String date = LocalDate.now().format(formatter);
		return String.format("%04d%s", bookIdGenerator.incrementAndGet(), date);
	}

}
