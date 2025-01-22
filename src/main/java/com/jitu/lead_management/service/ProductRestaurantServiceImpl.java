package com.jitu.lead_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.ProductRestaurant;
import com.jitu.lead_management.exception.DuplicateProductNameException;
import com.jitu.lead_management.exception.ProductRestaurantNotFoundException;
import com.jitu.lead_management.model.ProductRestaurantModificationModel;
import com.jitu.lead_management.model.ProductRestaurantViewModel;
import com.jitu.lead_management.model.QuotationProductRestaurantModificationModel;
import com.jitu.lead_management.repository.ProductRestaurantRepository;
import com.jitu.lead_management.utils.ProductUtils;

@Service
public class ProductRestaurantServiceImpl implements ProductRestaurantService {

    @Autowired
    private ProductRestaurantRepository productRestaurantRepository;

    @Override
    public void create(ProductRestaurantModificationModel productRestaurantModel) {
        ProductRestaurant productRestaurant = new ProductRestaurant(productRestaurantModel);
        save(productRestaurant);
    }

    @Override
    public int create(QuotationProductRestaurantModificationModel quotationProductRestaurant) {
        // Create ProductRestaurant from QuotationProductRestaurantModificationModel
        ProductRestaurant productRestaurant = new ProductRestaurant();
        productRestaurant.setName(quotationProductRestaurant.getName());
        productRestaurant.setAvgPerChildPrice(quotationProductRestaurant.getAvgPerChildPrice());
        productRestaurant.setAvgPerAdultPrice(quotationProductRestaurant.getAvgPerAdultPrice());

        productRestaurant = save(productRestaurant);
        return productRestaurant.getProductId();
    }

    @Override
    public ProductRestaurantViewModel findById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        ProductRestaurant productRestaurant = productRestaurantRepository.findById(intProductId)
                .orElseThrow(() -> new ProductRestaurantNotFoundException("Error: Product Restaurant not found"));

        return new ProductRestaurantViewModel(productRestaurant);
    }

    @Override
    public List<ProductRestaurantViewModel> findAll() {
        List<ProductRestaurant> productRestaurantList = productRestaurantRepository.findAll();
        return productRestaurantList.stream().map(ProductRestaurantViewModel::new).collect(Collectors.toList());
    }

    @Override
    public List<String> fetchProductNames() {
        return productRestaurantRepository.fetchProductNames();
    }

    @Override
    public void update(String productId, ProductRestaurantModificationModel productRestaurantModel) {
        int intProductId = ProductUtils.resolveProductId(productId);

        ProductRestaurant productRestaurant = productRestaurantRepository.findById(intProductId)
                .orElseThrow(() -> new ProductRestaurantNotFoundException("Error: Product Restaurant not found"));

        productRestaurant = ProductUtils.mapProductRestaurantUpdate(productRestaurant, productRestaurantModel);

        save(productRestaurant);
    }

    @Override
    public void deleteByIds(List<String> productIds) {
        List<Integer> intProductIds = productIds.stream().map(ProductUtils::resolveProductId)
                .collect(Collectors.toList());
        productRestaurantRepository.deleteByProductIdIn(intProductIds);
    }

    @Override
    public void deleteById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        productRestaurantRepository.deleteById(intProductId);
    }

    @Override
    public void deleteAll() {
        productRestaurantRepository.deleteAll();
    }

    private ProductRestaurant save(ProductRestaurant productRestaurant) {
        try {
            return productRestaurantRepository.save(productRestaurant);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Duplicate entry") || errorMessage.contains("uq_name_pr")) {
                throw new DuplicateProductNameException("Duplicate names are not allowed");
            } else {
                throw e;
            }
        }
    }
}
