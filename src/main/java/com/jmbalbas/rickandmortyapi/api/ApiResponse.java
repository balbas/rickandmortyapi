package com.jmbalbas.rickandmortyapi.api;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.Getter;

/**
 * Api response model.
 * 
 * @author Jose 
 */
@Getter
public class ApiResponse {
    private final Integer code;
    private final Object body;

    public ApiResponse(final int code, final JSONObject body) {
		this.code = code;
		this.body = body;
	}

	public ApiResponse(final int code, final JSONArray body) {
		this.code = code;
		this.body = body;
	}
}
