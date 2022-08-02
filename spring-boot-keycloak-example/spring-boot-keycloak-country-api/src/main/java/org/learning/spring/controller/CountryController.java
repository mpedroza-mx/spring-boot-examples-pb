package org.learning.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "countries")
public class CountryController {

  private static List<String> countries = new ArrayList<>();
  private static final String SERVICE_CONTEXT_PATH = "countries/";

  static {
    countries.add("Mexico");
    countries.add("Colombia");
    countries.add("Brazil");
  }

  @GetMapping
  public ResponseEntity listAllCountries() {
    return ResponseEntity.ok(countries);
  }

  @PostMapping
  public ResponseEntity addCountry(@RequestBody String[] request, UriComponentsBuilder builder) {
    countries.addAll(Arrays.asList(request));
    return ResponseEntity.created(
        builder.path(SERVICE_CONTEXT_PATH + (countries.size() - 1)).build().toUri()).build();
  }
}
