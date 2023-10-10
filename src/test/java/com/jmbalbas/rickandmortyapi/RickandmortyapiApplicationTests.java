package com.jmbalbas.rickandmortyapi;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jmbalbas.rickandmortyapi.api.ApiException;
import com.jmbalbas.rickandmortyapi.api.rest.HttpClient;
import com.jmbalbas.rickandmortyapi.models.CharacterAppearance;
import com.jmbalbas.rickandmortyapi.services.SearchCharacterAppearanceService;

import net.minidev.json.JSONObject;

/**
 * 
 * @author Jose 
 */
@RunWith(MockitoJUnitRunner.class)
public class RickandmortyapiApplicationTests {
	@InjectMocks
	private SearchCharacterAppearanceService searchCharacterAppearanceService;

	@Test
	public void getSearchCharacterAppearanceByName_Test() throws JsonMappingException, JsonProcessingException, 
			ApiException {
		String name = "Rick Sanchez";
		CharacterAppearance c = searchCharacterAppearanceService.getSearchCharacterAppearanceByName(name);
		MatcherAssert.assertThat("Rick Sanchez", Matchers.is(c.getName()));
	}

	@Test
	public void getStatusCode_Test() throws ApiException, JsonMappingException, JsonProcessingException {
		HttpClient<JSONObject> eClient = new HttpClient<JSONObject>("GET", 
				"https://rickandmortyapi.com/api/episode/1", JSONObject.class);
        eClient.execute();
		int statudCode = eClient.getStatusCode();
		MatcherAssert.assertThat(statudCode, Matchers.equalTo(HttpStatus.OK.value()));
	}
}
