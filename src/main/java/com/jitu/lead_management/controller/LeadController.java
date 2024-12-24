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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitu.lead_management.exception.LeadManagementException;
import com.jitu.lead_management.exception.UnknownErrorException;
import com.jitu.lead_management.model.LeadModificationModel;
import com.jitu.lead_management.model.LeadViewModel;
import com.jitu.lead_management.service.JWTService;
import com.jitu.lead_management.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private LeadService leadService;

    private static final Logger logger = LoggerFactory.getLogger(LeadController.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody LeadModificationModel lead,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            leadService.createLead(reference, lead);

            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("fetch")
    public List<LeadViewModel> getLeads(@RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            return leadService.getLeads(reference);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("fetch-by-id")
    public LeadViewModel getLeadById(@RequestParam String id,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            return leadService.getLeadById(id, reference);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam String id,
            @RequestBody LeadModificationModel lead,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            leadService.updateLead(reference, id, lead);

            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("delete-by-ids")
    public ResponseEntity<String> deleteLeadsByIds(@RequestParam List<String> ids,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            leadService.deleteLeadsByIds(ids, reference);

            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("delete-all")
    public ResponseEntity<String> deleteAllLeads(@RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            leadService.deleteAllLeads(reference);

            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("delete-by-id")
    public ResponseEntity<String> deleteByLeadId(@RequestParam String id,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            leadService.deleteByLeadId(id, reference);

            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/test")
    public String getMethodName(@RequestParam String param) {
        return new String("sucessful: " + param);
    }

}
