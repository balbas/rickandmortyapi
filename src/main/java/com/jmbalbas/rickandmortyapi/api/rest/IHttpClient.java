package com.jmbalbas.rickandmortyapi.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jmbalbas.rickandmortyapi.api.ApiException;

/**
 * HttpClient interface.
 * 
 * @author Jose
 */
public interface IHttpClient {
    void execute() throws ApiException;
    <T> T getResponse() throws ApiException, JsonMappingException, JsonProcessingException;
}
