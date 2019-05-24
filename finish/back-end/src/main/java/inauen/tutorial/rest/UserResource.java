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
@Path("user")
public class UserResource {

	/**
	 * Der UserManager kuemmert Sich um die InMemoryStore. Er kommt nur einmal vor
	 */

	@Inject
	private UserManager userManager;


	/**
	 * REST-API für HTTP-GET mit Parameter. Liefert einen User zurueck - benoetigt den Aufruf /ID
	 * @param id
	 * @return User (oder 404)
	 */
	@GET
	@Path( "/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam("id") String id) {

		User user = userManager.get(id);

		if (user != null)
		{
			return Response.ok(user).build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * REST-API fuer HTTP-GET ohne Parameter - soll die gesamte Liste an User zurueckgeben
	 * @return Liste aller User
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(userManager.getAll()).build();
	}

	/**
	 * REST-API fuer HTTP-POST. Erwartet ein JSON-Objekt im POST drin. Erstellt einen neuen User
	 * @param userJSON
	 * @return ID
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(JsonObject userJSON)
	{
		// Wir holen zuerst aus dem JSON unsere Werte
		String namen = userJSON.getString(User.ATTR_NAME);
		String ip = userJSON.getString(User.ATTR_IP);
		User newUser = new User(namen, ip);
		String id = userManager.add(newUser);
		return Response.ok(id).build();
	}

	/**
	 * Loescht Userobjekt aufgrund der ID
	 * @param id
	 * @return Userobjekt oder 404
	 */
	@DELETE
	@Path( "/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id)
	{
		User teachObjekt = userManager.remove(id);

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
	 * REST-API auf PUT-Methode. Userobjekt (referenziert ueber die ID) wird angepasst
	 * @param id
	 * @param userJSON
	 * @return Userobjekt oder 404
	 */

	/*@PUT
	@Path( "/{id}" )
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(@PathParam("id") String id, JsonObject userJSON)
	{
		// Wir holen zuerst aus dem JSON unsere Werte
		String namen = userJSON.getString(User.ATTR_NAME);
		String ip = userJSON.getString(User.ATTR_IP);

		User changed = userManager.merge(id, namen, ip);
		if (changed != null)
		{
			return Response.ok(changed).build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).build();
		}

	}*/


}
