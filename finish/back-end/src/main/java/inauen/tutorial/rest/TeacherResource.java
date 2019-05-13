package inauen.tutorial.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@RequestScoped
@Path("teachers")
public class TeacherResource {
	
	/**
	 * Der TeacherManager kuemmert Sich um die InMemoryStore. Er kommt nur einmal vor
	 */
	
	@Inject
	private TeacherManager teacherManager;

	
	/**
	 * REST-API für HTTP-GET mit Parameter. Liefert einen Lehrer zurueck - benoetigt den Aufruf /ID
	 * @param id
	 * @return Teacher (oder 404)
	 */
	@GET
	@Path( "/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam("id") String id) {
		
		Teacher teacher = teacherManager.get(id);
		
		if (teacher != null)
		{
			return Response.ok(teacher).build();	
		}
		else 
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * REST-API fuer HTTP-GET ohne Parameter - soll die gesamte Liste an Teacher zurueckgeben
	 * @return Liste aller Teacher
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(teacherManager.getAll()).build();
	}
	
	/**
	 * REST-API fuer HTTP-POST. Erwartet ein JSON-Objekt im POST drin. Erstellt einen neuen Lehrer
	 * @param teacherJSON
	 * @return ID
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(JsonObject teacherJSON)
	{	
		// Wir holen zuerst aus dem JSON unsere Werte
		String vornamen = teacherJSON.getString(Teacher.ATTR_VORNAME);
		String nachname = teacherJSON.getString(Teacher.ATTR_NACHNAME);
		String fachbereich = teacherJSON.getString(Teacher.ATTR_FACHBEREICH);
		
		
		
		
		Teacher newTeacher = new Teacher(vornamen, nachname, fachbereich);
		String id = teacherManager.add(newTeacher);
		return Response.ok(id).build();	
	}
	
	/**
	 * Loescht Teacherobjekt aufgrund der ID
	 * @param id
	 * @return Teacherobjekt oder 404
	 */
	@DELETE
	@Path( "/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id)
	{
		Teacher teachObjekt = teacherManager.remove(id);
		
		if (teachObjekt != null)
		{
			return Response.ok(teachObjekt).build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	/**
	 * REST-API auf PUT-Methode. Teacherobjekt (referenziert ueber die ID) wird angepasst
	 * @param id
	 * @param teacherJSON
	 * @return Teacherobjekt oder 404
	 */
	
	@PUT
	@Path( "/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(@PathParam("id") String id, JsonObject teacherJSON)
	{	
		// Wir holen zuerst aus dem JSON unsere Werte
		String vornamen = teacherJSON.getString(Teacher.ATTR_VORNAME);
		String nachname = teacherJSON.getString(Teacher.ATTR_NACHNAME);
		String fachbereich = teacherJSON.getString(Teacher.ATTR_FACHBEREICH);
		
		Teacher changed = teacherManager.merge(id, vornamen, nachname, fachbereich);
		if (changed != null)
		{
			return Response.ok(changed).build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).build();
		}
			
	}	
	
	
}
