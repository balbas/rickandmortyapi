package com.jmbalbas.rickandmortyapi.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Jose 
 */
@Getter
@Setter
public class Location extends ApiModel {
    private String type;
    private String dimension;
    private List<String> residents;
}
