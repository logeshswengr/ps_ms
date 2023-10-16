package com.project.theatreservice.controller;

import com.project.theatreservice.dtos.ShowRequest;
import com.project.theatreservice.dtos.ShowResponse;
import com.project.theatreservice.services.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping(value = "/")
    public ResponseEntity<ShowResponse> save(@RequestBody ShowRequest request) throws Exception {
        log.info("add new show request body={}", request);
        ShowResponse response = showService.saveShow(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShowResponse> get(@PathVariable Long id) throws Exception {
        ShowResponse response = showService.getShow(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ShowResponse> update(@PathVariable Long id, @RequestBody ShowRequest request) throws Exception {
        ShowResponse response = showService.updateShow(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        showService.deleteShow(id);
        return ResponseEntity.ok(null);
    }
}
