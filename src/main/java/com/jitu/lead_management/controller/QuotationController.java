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
import com.jitu.lead_management.model.QuotationModificationModel;
import com.jitu.lead_management.model.QuotationViewModel;
import com.jitu.lead_management.service.JWTService;
import com.jitu.lead_management.service.QuotationService;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private JWTService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(QuotationController.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody QuotationModificationModel quotation,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);
            quotationService.create(reference, quotation);
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch-by-id")
    public QuotationViewModel findById(@RequestParam String id) {
        try {
            return quotationService.findById(id);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch")
    public List<QuotationViewModel> findAll() {
        try {
            return quotationService.findAll();
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam String id,
            @RequestBody QuotationModificationModel quotation) {
        try {
            quotationService.update(id, quotation);
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
            quotationService.deleteByIds(ids);
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    // @DeleteMapping("/delete-by-id")
    // public ResponseEntity<String> deleteById(@RequestParam String id) {
    // try {
    // quotationService.deleteById(id);
    // return new ResponseEntity<>("Sucess", HttpStatus.OK);
    // } catch (LeadManagementException e) {
    // throw e;
    // } catch (Exception e) {
    // logger.error("Unknown error: " + e.getMessage(), e);
    // throw new UnknownErrorException("Error: unknown error");
    // }
    // }

    // @DeleteMapping("/delete-all")
    // public ResponseEntity<String> deleteAll() {
    // try {
    // quotationService.deleteAll();
    // return new ResponseEntity<>("Sucess", HttpStatus.OK);
    // } catch (LeadManagementException e) {
    // throw e;
    // } catch (Exception e) {
    // logger.error("Unknown error: " + e.getMessage(), e);
    // throw new UnknownErrorException("Error: unknown error");
    // }
    // }

}
