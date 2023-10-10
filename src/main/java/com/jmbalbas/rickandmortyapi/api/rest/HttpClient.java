package com.jmbalbas.rickandmortyapi.api.rest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmbalbas.rickandmortyapi.api.ApiException;
import com.jmbalbas.rickandmortyapi.api.ApiResponse;

/**
 * Class implementation of IHttpClient.
 * Core for the execution of HTTP requests and processing of responses.
 * 
 * @author Jose
 */
public class HttpClient<T> implements IHttpClient {
    private final transient Class<T> typeClass;

    private final String method;
	private final String url;

	private Map<String, Object> parameters;
    private String body;
    private HttpURLConnection connection;
    protected ApiResponse apiResponse = null;

    public HttpClient(final String method, final String url, Map<String, Object> parameters, 
            String body, Class<T> typeClass) {
        this.method = method;
        this.url = url;
        this.parameters = parameters;
        this.body = body;
        this.typeClass = typeClass;
    }

    public HttpClient(final String method, final String url, Class<T> typeClass) {
        this(method, url, null, null, typeClass);
    }

    public void execute() throws ApiException {
        try {
            URL u = new URL(url + (parameters != null ? parametersToUrl() : ""));
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod(method);
            if (method.equals("POST")) {
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.write(body.getBytes(StandardCharsets.UTF_8));
            }

            InputStream responseStream;
            int responseCode;
			try {
				responseStream = connection.getInputStream();
				responseCode = connection.getResponseCode();
			} catch (IOException e) {
				responseStream = connection.getErrorStream();
				responseCode = connection.getResponseCode();
            }

            String response = IOUtils.toString(responseStream, StandardCharsets.UTF_8);
            Object json = new JSONTokener(response).nextValue();
            if (json instanceof JSONObject) {
                apiResponse = new ApiResponse(responseCode, new JSONObject(response));
            } else {
                apiResponse = new ApiResponse(responseCode, new JSONArray(response));
            }
        } catch (Exception e) {
            throw ApiException.getMessage(e);
        } finally {
            connection.disconnect();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getResponse() throws ApiException, JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object body = apiResponse.getBody();
        if (body instanceof JSONObject) {
            return (T) mapper.readValue(apiResponse.getBody().toString(), typeClass);
        } else {
            return (T) mapper.readValue(apiResponse.getBody().toString(), 
                    mapper.getTypeFactory().constructCollectionType(List.class, typeClass));
        }
    }

    /**
     * Response status code.
     * 
     * @return
     */
    public int getStatusCode() {
        return apiResponse.getCode();
    }

    /**
     * Method to add parameters to url.
     * 
     * @return
     * @throws Exception
     */
    private String parametersToUrl() throws Exception {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
            if (s.length() > 0) {
                s.append("&");
            }
            s.append(URLEncoder.encode(parameter.getKey(), StandardCharsets.UTF_8));
            s.append("=");
            s.append(URLEncoder.encode(parameter.getValue().toString(), StandardCharsets.UTF_8));
        }

        return s.toString();
    }
}
