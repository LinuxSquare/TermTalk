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
public class TeacherManager {
	
	/**
	 * Zugriffe sollen parallel stattfinden dürfen
	 */
	private ConcurrentMap<String, Teacher> inMemStore = new ConcurrentHashMap<>();
	
	/**
	 * Brauchen wir um ID zu generieren
	 */
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	private AtomicInteger bookIdGenerator = new AtomicInteger(0);
	
	
	/**
	 * Konstruktor
	 */
	public TeacherManager()
	{
		// Dummy Daten
		String id1 = getNextId();
		Teacher t1 = new Teacher("VornameTest1", "NachnameTest1", "Informatik");
		t1.setId(id1);

		String id2 = getNextId();
		Teacher t2 = new Teacher("VornameTest2", "NachnameTest2", "Informatik");
		t2.setId(id2);

		String id3 = getNextId();
		Teacher t3 = new Teacher("VornameTest3", "NachnameTest3", "Informatik");
		t3.setId(id3);

		String id4 = getNextId();
		Teacher t4 = new Teacher("VornameTest4", "NachnameTest4", "Informatik");
		t4.setId(id4);

		
		inMemStore.put(id1, t1);
		inMemStore.put(id2, t2);
		inMemStore.put(id3, t3);
		inMemStore.put(id4, t4);
		}
	
	
	/**
	 * Fügt der HashMap einen neuen Teacher hinzu
	 * 
	 * @param teacher
	 * @return ID des Teachers
	 */
	public String add(Teacher teacher)
	{
		String id = getNextId();
		teacher.setId(id);
		inMemStore.put(id, teacher);
		return id;
	}
	
	/**
	 * 
	 * @param id - des Teachers
	 * @return Teacher-Objekt
	 */
	public Teacher get(String id)
	{
		return inMemStore.get(id);
	}
	
	/**
	 * Gibt eine List aller Teachers zurück
	 * @return List<Teacher>
	 */
	public List<Teacher> getAll()
	{
		List<Teacher> teachers = new ArrayList<>();
		teachers.addAll(inMemStore.values());
		return teachers;
	}
	
	/**
	 * Loescht Teacher aus Datenbank
	 * @param id
	 * @return Geloeschtes Objekt oder null
	 */
	public Teacher remove(String id)
	{
		if (inMemStore.containsKey(id))
		{
			Teacher teacher = inMemStore.get(id);
			inMemStore.remove(id);
			return teacher;
		}
		else 
		{
			return null;
		}
	}
	
	/**
	 * Aendert einen Teacher in seinen Attributen
	 * 
	 * @param id
	 * @param pvornamen
	 * @param pnachnamen
	 * @param pfachbereich
	 * @return Teacherobjekt oder null
	 */
	public Teacher merge(String id, String pvornamen, String pnachnamen, String pfachbereich)
	{
		Teacher tochange = inMemStore.get(id);
		
		if (tochange != null)
		{
			tochange.setFachbereich(pfachbereich);
			tochange.setNachname(pnachnamen);
			tochange.setVorname(pvornamen);
			
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
		return String.format("%04d-%s", bookIdGenerator.incrementAndGet(), date);
	}

}
