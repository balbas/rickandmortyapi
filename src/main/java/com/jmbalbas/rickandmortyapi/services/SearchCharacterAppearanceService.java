package com.jmbalbas.rickandmortyapi.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jmbalbas.rickandmortyapi.api.ApiException;
import com.jmbalbas.rickandmortyapi.api.rest.SearchClient;
import com.jmbalbas.rickandmortyapi.models.CharacterAppearance;

/**
 * Service class to manager the API.
 * 
 * @author Jose 
 */
@Service
public class SearchCharacterAppearanceService {
    public CharacterAppearance getSearchCharacterAppearanceByName(String name) 
            throws JsonMappingException, JsonProcessingException, ApiException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        SearchClient<CharacterAppearance> searchClient = new SearchClient<CharacterAppearance>(parameters);

        searchClient.execute();

        return searchClient.getResponse();
    }
}
