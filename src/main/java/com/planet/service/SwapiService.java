package com.planet.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.swapi.model.Planetsw;

public class SwapiService {

	private Client config() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		return client;
	}

	public Integer find(Integer idPlanet) {

		Client client = config();

		WebResource webResourceGet = client.resource("https://swapi.co/api/planets/" + idPlanet);
		ClientResponse response = webResourceGet.get(ClientResponse.class);
		Planetsw planetsw = response.getEntity(Planetsw.class);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Erro : " + response.getStatus());
		}
		
		return planetsw.getFilms().size();


	}

}
