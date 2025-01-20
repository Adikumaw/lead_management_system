package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.ProductHotelModificationModel;
import com.jitu.lead_management.model.ProductHotelViewModel;
import com.jitu.lead_management.model.QuotationProductHotelModificationModel;

public interface ProductHotelService {
    void create(ProductHotelModificationModel productHotelModel);

    int create(QuotationProductHotelModificationModel quotationProductHotel);

    ProductHotelViewModel findById(String productId);

    List<ProductHotelViewModel> findAll();

    List<String> fetchProductNames();

    void update(String productId, ProductHotelModificationModel productHotelModel);

    void deleteByIds(List<String> productIds);

    void deleteById(String id);

    void deleteAll();

}
