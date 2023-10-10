package com.jmbalbas.rickandmortyapi.api;

import org.springframework.http.HttpMethod;

import lombok.Getter;
import lombok.Setter;

/**
 * Api request model.
 * 
 * @author Jose 
 */
@Getter
@Setter
public class ApiRequest {
    private HttpMethod method;
    private String name;
}
