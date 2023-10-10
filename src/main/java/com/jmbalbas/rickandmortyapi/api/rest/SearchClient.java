package com.jmbalbas.rickandmortyapi.api.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jmbalbas.rickandmortyapi.api.ApiException;
import com.jmbalbas.rickandmortyapi.models.CharacterAppearance;
import com.jmbalbas.rickandmortyapi.models.Episode;

/**
 * Search client class.
 * 
 * @author Jose 
 */
public class SearchClient<T> extends HttpClient<T> {
    @SuppressWarnings("unchecked")
    public SearchClient(Map<String, Object> parameters) {
        super("GET", "https://rickandmortyapi.com/api/character/?", parameters, null, 
                (Class<T>) CharacterAppearance.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public CharacterAppearance getResponse() throws ApiException,  JsonMappingException, JsonProcessingException {
        CharacterAppearance characterAppearance = new CharacterAppearance();
        List<Integer> episodesIds = new ArrayList<>();
        
        JSONArray result = ((JSONObject) apiResponse.getBody()).getJSONArray("results");
        for (int i = 0; i < result.length(); i++) {
            JSONObject cObj = result.getJSONObject(i);
            // Always set the first name found
            if (i == 0) {
                characterAppearance.setName(cObj.getString("name"));
            }

            // Get 'episode' array. Is an array of urls from each episode
            JSONArray episodes = cObj.getJSONArray("episode");
            for (int j = 0; j < episodes.length(); j++) {
                String episodeUrl = episodes.getString(j);
                // Get episode id from url and add to list
                String episodeId = episodeUrl.split("/")[episodeUrl.split("/").length - 1];
                if (!episodesIds.contains(Integer.valueOf(episodeId))) {
                    episodesIds.add(Integer.valueOf(episodeId));
                }
            }
        }

        // Build url to get multiple episodes
        // <a href="https://rickandmortyapi.com/documentation/#get-multiple-episodes">Get multiple episodes</a>
        String episodesUrl = "https://rickandmortyapi.com/api/episode/";
        // Sort list
        Collections.sort(episodesIds);
        for (int j = 0; j < episodesIds.size(); j++) {
            if (j > 0) {
                episodesUrl += ",";
            }
            // Concat ids
            episodesUrl += episodesIds.get(j);
        }

        HttpClient<Episode> eClient = new HttpClient<Episode>("GET", episodesUrl, Episode.class);
        eClient.execute();
        List<Episode> episodesList = eClient.getResponse();
        List<String> episodesNameList = new ArrayList<>();
        int initial = 0;
        for (Episode e : episodesList) {
            // The first episode in array is the first appearance
            if (initial == 0) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, u", 
                        Locale.ENGLISH);
                LocalDate date = LocalDate.parse(e.getAirDate(), dateFormatter);
                characterAppearance.setFirstAppearance(date.toString());
                initial++;
            }
            episodesNameList.add(e.getName());
        }
        characterAppearance.setEpisodes(episodesNameList.toArray(new String[0]));

        return characterAppearance;
    }
}
