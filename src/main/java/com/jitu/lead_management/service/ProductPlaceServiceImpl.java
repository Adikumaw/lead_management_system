package com.jitu.lead_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.ProductPlace;
import com.jitu.lead_management.exception.DuplicateProductNameException;
import com.jitu.lead_management.exception.ProductPlaceNotFoundException;
import com.jitu.lead_management.model.ProductPlaceModificationModel;
import com.jitu.lead_management.model.ProductPlaceViewModel;
import com.jitu.lead_management.model.QuotationProductPlaceModificationModel;
import com.jitu.lead_management.repository.ProductPlaceRepository;
import com.jitu.lead_management.utils.ProductUtils;

@Service
public class ProductPlaceServiceImpl implements ProductPlaceService {

    @Autowired
    private ProductPlaceRepository productPlaceRepository;

    @Override
    public void create(ProductPlaceModificationModel productPlaceModel) {
        ProductPlace productPlace = new ProductPlace(productPlaceModel);
        save(productPlace);
    }

    @Override
    public int create(QuotationProductPlaceModificationModel quotationProductPlace) {
        return 0;
    }

    @Override
    public ProductPlaceViewModel findById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        ProductPlace productPlace = productPlaceRepository.findById(intProductId)
                .orElseThrow(() -> new ProductPlaceNotFoundException("Error: Product Place not found"));

        return new ProductPlaceViewModel(productPlace);
    }

    @Override
    public List<ProductPlaceViewModel> findAll() {
        List<ProductPlace> productPlaceList = productPlaceRepository.findAll();
        return productPlaceList.stream().map(ProductPlaceViewModel::new).collect(Collectors.toList());
    }

    @Override
    public List<String> fetchProductNames() {
        return productPlaceRepository.fetchProductNames();
    }

    @Override
    public void update(String productId, ProductPlaceModificationModel productPlaceModel) {
        int intProductId = ProductUtils.resolveProductId(productId);

        ProductPlace productPlace = productPlaceRepository.findById(intProductId)
                .orElseThrow(() -> new ProductPlaceNotFoundException("Error: Product Place not found"));

        productPlace = ProductUtils.mapProductPlaceUpdate(productPlace, productPlaceModel);

        save(productPlace);
    }

    @Override
    public void deleteByIds(List<String> productIds) {
        List<Integer> intProductIds = productIds.stream().map(ProductUtils::resolveProductId)
                .collect(Collectors.toList());
        productPlaceRepository.deleteByProductIdIn(intProductIds);
    }

    @Override
    public void deleteById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        productPlaceRepository.deleteById(intProductId);
    }

    @Override
    public void deleteAll() {
        productPlaceRepository.deleteAll();
    }

    private ProductPlace save(ProductPlace productPlace) {
        try {
            return productPlaceRepository.save(productPlace);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Duplicate entry") || errorMessage.contains("uq_name_pp")) {
                throw new DuplicateProductNameException("Duplicate names are not allowed");
            } else {
                throw e;
            }
        }
    }
}
