package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.ProductCarModificationModel;
import com.jitu.lead_management.model.ProductCarViewModel;
import com.jitu.lead_management.model.QuotationProductCarModificationModel;

public interface ProductCarService {
    void create(ProductCarModificationModel productCarModel);

    int create(QuotationProductCarModificationModel quotationProductCar);

    ProductCarViewModel findById(String productId);

    List<ProductCarViewModel> findAll();

    List<String> fetchProductNames();

    void update(String productId, ProductCarModificationModel productCarModel);

    void deleteByIds(List<String> productIds);

    void deleteById(String id);

    void deleteAll();
}
