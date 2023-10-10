package com.jmbalbas.rickandmortyapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Character appearance model.
 * 
 * @author Jose 
 */
@Getter
@Setter
public class CharacterAppearance {
    String name;
    String[] episodes;
    @JsonProperty("first_appearance") String firstAppearance;
}
