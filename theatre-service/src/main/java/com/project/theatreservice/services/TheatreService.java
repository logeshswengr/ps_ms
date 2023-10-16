package com.project.theatreservice.services;

import com.project.theatreservice.dtos.CityResponse;
import com.project.theatreservice.dtos.ShowResponse;

import com.project.theatreservice.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TheatreService {

    private TheatreRepository theatreRepository;

    @Autowired
    public TheatreService( TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }
    public List<ShowResponse> getTheatresFromCityAndMovie(String cityId, String movieId)  {
        List<ShowResponse> list = theatreRepository.findTheatreByCityandMovie(cityId, movieId);
        return list;
    }
}
