package com.jmbalbas.rickandmortyapi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring application.
 * 
 * @author Jose 
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.jmbalbas.rickandmortyapi.controllers")
public class RickandmortyapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(RickandmortyapiApplication.class, args);
	}
}
