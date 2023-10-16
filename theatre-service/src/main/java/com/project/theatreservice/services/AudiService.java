package com.project.theatreservice.services;

import com.project.theatreservice.dtos.AudiRequest;
import com.project.theatreservice.dtos.AudiResponse;
import com.project.theatreservice.entity.Audi;
import com.project.theatreservice.entity.Theatre;
import com.project.theatreservice.repositories.AudiRepository;
import com.project.theatreservice.repositories.TheatreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AudiService {
    @Autowired
    private AudiRepository audiRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    public AudiResponse saveAudi(AudiRequest request) throws Exception {
        Optional<Theatre> theatre = theatreRepository.findById(request.getTheatreId());
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        Audi audiSaved = audiRepository.save(new Audi(null, request.getNumber(),
                request.getMaxRows(), request.getMaxColumns(),  theatreDb, request.getSupportedFeatures()));

        return new AudiResponse(audiSaved.getId(), audiSaved.getNumber(), audiSaved.getMaxRows(),
                audiSaved.getMaxColumns(),
                audiSaved.getTheatre(), audiSaved.getSupportedFeatures());
    }


    public AudiResponse getAudi(Long id) throws Exception {
        Optional<Audi> audi = audiRepository.findById(id);
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();
        return new AudiResponse(audiDb.getId(), audiDb.getNumber(), audiDb.getMaxRows(),
                audiDb.getMaxColumns(),
                audiDb.getTheatre(), audiDb.getSupportedFeatures());
    }

    public AudiResponse updateAudi(Long id, AudiRequest request) throws Exception {
        Optional<Audi> audi = audiRepository.findById(id);
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();
        Optional<Theatre> theatre = theatreRepository.findById(request.getTheatreId());
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        audiDb.setNumber(request.getNumber());
        audiDb.setTheatre(theatreDb);
        audiDb.setMaxRows(request.getMaxRows());
        audiDb.setMaxColumns(request.getMaxColumns());
        audiDb.setSupportedFeatures(request.getSupportedFeatures());
        Audi audiSaved = audiRepository.save(audiDb);

        return new AudiResponse(audiSaved.getId(), audiSaved.getNumber(), audiSaved.getMaxRows(),
                audiSaved.getMaxColumns(),
                audiSaved.getTheatre(), audiSaved.getSupportedFeatures());
    }

    public void deleteAudi(Long id) throws Exception {
        audiRepository.deleteById(id);
    }
}
