package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.ProductRestaurantModificationModel;
import com.jitu.lead_management.model.ProductRestaurantViewModel;
import com.jitu.lead_management.model.QuotationProductRestaurantModificationModel;

public interface ProductRestaurantService {
    void create(ProductRestaurantModificationModel productRestaurantModel);

    int create(QuotationProductRestaurantModificationModel quotationProductRestaurant);

    ProductRestaurantViewModel findById(String productId);

    List<ProductRestaurantViewModel> findAll();

    List<String> fetchProductNames();

    void update(String productId, ProductRestaurantModificationModel productRestaurantModel);

    void deleteByIds(List<String> productIds);

    void deleteById(String id);

    void deleteAll();
}
