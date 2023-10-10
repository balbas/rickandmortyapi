package com.jmbalbas.rickandmortyapi.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Character model.
 * 
 * @author Jose 
 */
public class Character extends ApiModel {
	@Getter
	@Setter
	private Status status;

	@Getter
	@Setter
	private String species;

	@Getter
	@Setter
	private String type;

	@Getter
	@Setter
	private Gender gender;

	@Getter
	@Setter
	private Location origin;

	@Getter
	@Setter
	private Location location;

	@Getter
	@Setter
	private String image;

	@Getter
	@Setter
	private List<String> episode;

	enum Status {
		@JsonProperty("Alive")
		ALIVE,

		@JsonProperty("Dead")
		DEAD,

		@JsonProperty("unknown")
		UNKNOWN
	}

	enum Gender {
		@JsonProperty("Female")
		FEMALE,

		@JsonProperty("Male")
		MALE,

		@JsonProperty("Genderless")
		GENDERLESS,

		@JsonProperty("unknown")
		UNKNOWN
	}
}
