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
import com.jitu.lead_management.model.ProductRestaurantModificationModel;
import com.jitu.lead_management.model.ProductRestaurantViewModel;
import com.jitu.lead_management.service.ProductRestaurantService;

@RestController
@RequestMapping("/api/product/restaurants")
public class ProductRestaurantController {

    @Autowired
    private ProductRestaurantService productRestaurantService;

    private static final Logger logger = LoggerFactory.getLogger(ProductRestaurantController.class);

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProductRestaurantModificationModel productRestaurant) {
        try {
            productRestaurantService.create(productRestaurant);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch-by-id")
    public ProductRestaurantViewModel findById(@RequestParam String id) {
        try {
            return productRestaurantService.findById(id);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch")
    public List<ProductRestaurantViewModel> findAll() {
        try {
            return productRestaurantService.findAll();
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/fetch-product-names")
    public List<String> fetchProductNames() {
        try {
            return productRestaurantService.fetchProductNames();
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam String id,
            @RequestBody ProductRestaurantModificationModel productRestaurant) {
        try {
            productRestaurantService.update(id, productRestaurant);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @DeleteMapping("/delete-by-ids")
    public ResponseEntity<String> deleteByIds(@RequestParam List<String> ids) {
        try {
            productRestaurantService.deleteByIds(ids);
            return new ResponseEntity<>("Success", HttpStatus.OK);
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
            productRestaurantService.deleteById(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
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
            productRestaurantService.deleteAll();
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }
}