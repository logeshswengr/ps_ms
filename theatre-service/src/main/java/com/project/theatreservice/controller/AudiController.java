package com.project.theatreservice.controller;

import com.project.theatreservice.dtos.AudiRequest;
import com.project.theatreservice.dtos.AudiResponse;
import com.project.theatreservice.services.AudiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/audi")
public class AudiController {
    @Autowired
    private AudiService audiService;
    @PostMapping(value = "/")
    public ResponseEntity<AudiResponse> save(@RequestBody AudiRequest audiRequest) throws Exception {
        AudiResponse response = audiService.saveAudi(audiRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AudiResponse> update(@PathVariable Long id, @RequestBody AudiRequest audiRequest) throws Exception {
        AudiResponse response = audiService.updateAudi(id, audiRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        audiService.deleteAudi(id);
        return ResponseEntity.ok(null);
    }

}
