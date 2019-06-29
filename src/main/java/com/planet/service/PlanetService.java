package com.planet.service;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.planet.dao.PlanetDao;
import com.planet.model.Planet;

@Path("")
public class PlanetService {

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response insert(Planet planet) {

		ResponseBuilder status = Response.status(BAD_REQUEST);

		Integer qtdFilms = new SwapiService().find(planet.getId());
		planet.setFilms(qtdFilms);

		if (PlanetDao.getInstance().insert(planet)) {
			status = Response.status(CREATED);
		}

		return status.build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response list() {

		ResponseBuilder status = Response.status(NOT_FOUND);

		GenericEntity<List<Planet>> booksEntity = new GenericEntity<List<Planet>>(PlanetDao.getInstance().list()) {
		};

		status = Response.ok(booksEntity);

		return status.build();

	}

	@GET
	@Path("/swapi")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response listSwapi() {

		/**
		 * TODO FAZER
		 * 
		 */

		return null;

	}

	@DELETE
	@Path("{idPlanet}")
	public Response delete(@PathParam("idPlanet") Integer idPlanet) {

		ResponseBuilder status = Response.status(NOT_FOUND);

		if (PlanetDao.getInstance().delete(idPlanet)) {
			status = Response.status(NO_CONTENT);
		}

		return status.build();
	}

	@GET
	@Path("/{idPlanet}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response read(@PathParam("idPlanet") Integer idPlanet) {

		ResponseBuilder status = Response.status(NOT_FOUND);

		Planet planet = PlanetDao.getInstance().read(idPlanet);
		if (planet != null) {
			status = Response.ok(planet);
		}

		return status.build();
	}

}
