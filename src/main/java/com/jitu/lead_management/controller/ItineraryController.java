package com.jitu.lead_management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitu.lead_management.exception.LeadManagementException;
import com.jitu.lead_management.exception.UnknownErrorException;
import com.jitu.lead_management.model.ItineraryModificationModel;
import com.jitu.lead_management.model.ItineraryViewModel;
import com.jitu.lead_management.service.ItineraryService;

@RestController
@RequestMapping("/api/itinerary")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    private static final Logger logger = LoggerFactory.getLogger(ItineraryController.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ItineraryModificationModel itinerary) {
        try {
            itineraryService.createItinerary(itinerary);
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch-by-id")
    public ItineraryViewModel findById(@RequestParam String id) {
        try {
            return itineraryService.findById(id);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch")
    public List<ItineraryViewModel> findAll() {
        try {
            return itineraryService.findAll();
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam String id,
            @RequestBody ItineraryModificationModel itinerary) {
        try {
            itineraryService.update(id, itinerary);
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("/delete-by-ids")
    public ResponseEntity<String> deleteByIds(
            @RequestParam List<String> ids) {
        try {
            itineraryService.deleteByIds(ids);
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<String> deleteById(@RequestParam String id) {
        try {
            itineraryService.deleteById(id);
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll() {
        try {
            itineraryService.deleteAll();
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

}
