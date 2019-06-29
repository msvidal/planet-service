package com.planet.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet implements Serializable {

	private static final long serialVersionUID = -3289806249646110825L;

	private Integer id;
	private String name;
	private String climate;
	private String terrain;
	private Integer films;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getFilms() {
		return films;
	}

	public void setFilms(Integer films) {
		this.films = films;
	}

}
