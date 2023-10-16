package com.project.theatreservice.services;

import com.project.theatreservice.dtos.ShowRequest;
import com.project.theatreservice.dtos.ShowResponse;
import com.project.theatreservice.entity.Audi;
import com.project.theatreservice.entity.Show;
import com.project.theatreservice.repositories.AudiRepository;
import com.project.theatreservice.repositories.ShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private AudiRepository audiRepository;
    @Autowired
    private ShowRepository showRepository;
    public ShowResponse saveShow(ShowRequest request) throws Exception {
        Optional<Audi> audi = audiRepository.findById(request.getAudiId());
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();
        Show showDb = showRepository.save(new Show(null,  audiDb, request.getMovieId(), request.getStartTime(),
                request.getEndTime(), request.getShowStatus(), request.getMaxTickets()));

        return new ShowResponse(showDb.getId(), showDb.getAudi(), showDb.getMovieId(), showDb.getStartTime(), showDb.getEndTime(),
                showDb.getShowStatus(), showDb.getMaxTickets());
    }
    public ShowResponse getShow(Long id) throws Exception {
        Optional<Show> show = showRepository.findById(id);
        if (show.isEmpty()) {
            throw new EntityNotFoundException("Show not found");
        }
        Show showDb = show.get();
        return new ShowResponse(showDb.getId(), showDb.getAudi(), showDb.getMovieId(), showDb.getStartTime(), showDb.getEndTime(),
                showDb.getShowStatus(), showDb.getMaxTickets());
    }

    public ShowResponse updateShow(Long id, ShowRequest request) throws Exception {
        Optional<Show> show = showRepository.findById(id);
        if (show.isEmpty()) {
            throw new EntityNotFoundException("Show not found");
        }
        Show showDb = show.get();
        Optional<Audi> audi = audiRepository.findById(request.getAudiId());
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();

        showDb.setAudi(audiDb);
        showDb.setMovieId(request.getMovieId());
        showDb.setStartTime(request.getStartTime());
        showDb.setEndTime(request.getEndTime());
        showDb.setShowStatus(request.getShowStatus());
        showDb.setMaxTickets(request.getMaxTickets());
        return new ShowResponse(showDb.getId(), showDb.getAudi(), showDb.getMovieId(), showDb.getStartTime(), showDb.getEndTime(),
                showDb.getShowStatus(), showDb.getMaxTickets());
    }

    public void deleteShow(Long id) throws Exception {
        showRepository.deleteById(id);
    }


}
