package com.jmbalbas.rickandmortyapi.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jmbalbas.rickandmortyapi.api.ApiException;
import com.jmbalbas.rickandmortyapi.models.CharacterAppearance;
import com.jmbalbas.rickandmortyapi.services.SearchCharacterAppearanceService;

/**
 * Search character appearance controller.
 * 
 * @author Jose 
 */
@RestController
@RequestMapping("/search-character-appearance")
public class SearchCharacterAppearanceController {
    @Autowired
    private SearchCharacterAppearanceService searchCharacterAppearanceService;
    
    @GetMapping
    public CharacterAppearance getSearchCharacterAppearanceByName(@RequestParam String name) 
            throws JsonMappingException, JsonProcessingException, JSONException, ApiException {
        return searchCharacterAppearanceService.getSearchCharacterAppearanceByName(name);
    }
}
