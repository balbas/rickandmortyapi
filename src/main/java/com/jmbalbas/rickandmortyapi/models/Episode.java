package com.jmbalbas.rickandmortyapi.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Episode model.
 * 
 * @author Jose 
 */
@Getter
@Setter
public class Episode extends ApiModel {
    @JsonProperty("air_date") private String airDate;
    private String episode;
    private List<String> characters;
}
