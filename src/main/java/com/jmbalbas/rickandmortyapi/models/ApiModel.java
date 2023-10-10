package com.jmbalbas.rickandmortyapi.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Common attributes for models.
 * 
 * @author Jose 
 */
@Getter
@Setter
public class ApiModel {
    private Integer id;
    private String name;
    private String url;
    private String created;
}
