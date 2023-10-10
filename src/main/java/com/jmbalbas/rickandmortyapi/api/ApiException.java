package com.jmbalbas.rickandmortyapi.api;

/**
 * Class to manager API exceptions.
 * 
 * @author Jose 
 */
public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }

    public static ApiException getMessage(final Exception error) {
		return new ApiException(error.getMessage());
	}
}
