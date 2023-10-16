package com.project.theatreservice.services;

import com.project.theatreservice.dtos.CityRequest;
import com.project.theatreservice.dtos.CityResponse;
import com.project.theatreservice.entity.City;

import com.project.theatreservice.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public CityResponse saveCity(CityRequest cityRequest) {
        City cityObj = cityRepository.save(new City(null, cityRequest.getCity()));
        return new CityResponse(cityObj.getId(), cityObj.getCity());
    }

    public List<CityResponse> getCities() {
        List<City> cities = cityRepository.findAll();
        List<CityResponse> cityResponseList = cities.stream().map(city -> new CityResponse(city.getId(), city.getCity())).toList();
        return cityResponseList;
    }

}
