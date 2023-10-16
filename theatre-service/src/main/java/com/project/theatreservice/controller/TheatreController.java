package com.project.theatreservice.controller;

import com.project.theatreservice.dtos.CityResponse;
import com.project.theatreservice.dtos.ShowResponse;
import com.project.theatreservice.services.CityService;
import com.project.theatreservice.services.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;
    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public ResponseEntity<List<CityResponse>> getCities() {
        return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }
    @GetMapping("/city/movie/{cityId}/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ShowResponse>> getTheatreListFromMovie(@PathVariable String cityId, @PathVariable String movieId) {
        return new ResponseEntity<>(theatreService.getTheatresFromCityAndMovie(cityId, movieId), HttpStatus.OK);
    }
}
