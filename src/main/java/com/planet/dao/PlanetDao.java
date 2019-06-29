package com.planet.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.planet.model.Planet;

public class PlanetDao {

	private DBCollection dbDBCollection;

	private static PlanetDao instance = null;

	public static PlanetDao getInstance() {
		if (instance == null) {
			instance = new PlanetDao();
		}
		return instance;
	}

	protected PlanetDao() {
		init();
	}

	public void init() {
		try {

			MongoClientURI uri = new MongoClientURI(
					"mongodb+srv://msvidal:<password>@cluster0-0k5k6.mongodb.net/test?retryWrites=true&w=majority");

			MongoClient mongoClient = new MongoClient(uri);
//			MongoDatabase database = mongoClient.getDatabase("planetDatabase");
			DB database = mongoClient.getDB("planetDatabase");
			dbDBCollection = database.getCollection("planet");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean insert(Planet planet) {
		dbDBCollection.insert(toDBObject(planet));
		return true;
	}

	public boolean delete(Integer idPlanet) {
		DBObject query = new BasicDBObject("id", idPlanet);
		WriteResult result = dbDBCollection.remove(query);

		if (result.getN() == 1) {
			return true;
		}
		return false;

	}

	public Planet read(Integer idPlanet) {
		DBObject query = new BasicDBObject("idPlanet", idPlanet);
		DBObject dbObject = dbDBCollection.findOne(query);

		if (dbObject != null) {
			Planet planet = (Planet) fromDBObject(dbObject, Planet.class);
			return planet;
		}
		return null;
	}

	public List<Planet> list() {
		final List<Planet> planets = new ArrayList<>();

		try (DBCursor cursor = dbDBCollection.find()) {
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();
				planets.add((Planet) fromDBObject(dbObject, Planet.class));
			}
		}
		return planets;
	}

	public static DBObject toDBObject(Object pojo) {
		String json = new Gson().toJson(pojo);
		return (DBObject) JSON.parse(json);
	}

	@SuppressWarnings("unchecked")
	public static Object fromDBObject(DBObject dbObj, @SuppressWarnings("rawtypes") Class clazz) {
		String json = dbObj.toString();
		return new Gson().fromJson(json, clazz);
	}

}
