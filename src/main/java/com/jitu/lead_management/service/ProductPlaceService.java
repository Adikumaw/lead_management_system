package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.ProductPlaceModificationModel;
import com.jitu.lead_management.model.ProductPlaceViewModel;
import com.jitu.lead_management.model.QuotationProductPlaceModificationModel;

public interface ProductPlaceService {
    void create(ProductPlaceModificationModel productPlaceModel);

    int create(QuotationProductPlaceModificationModel quotationProductPlace);

    ProductPlaceViewModel findById(String productId);

    List<ProductPlaceViewModel> findAll();

    List<String> fetchProductNames();

    void update(String productId, ProductPlaceModificationModel productPlaceModel);

    void deleteByIds(List<String> productIds);

    void deleteById(String id);

    void deleteAll();
}
