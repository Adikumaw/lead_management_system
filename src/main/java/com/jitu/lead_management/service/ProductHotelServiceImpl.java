package com.jitu.lead_management.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.ProductHotel;
import com.jitu.lead_management.exception.DuplicateProductNameException;
import com.jitu.lead_management.exception.ProductHotelNotFoundException;
import com.jitu.lead_management.model.ProductHotelModificationModel;
import com.jitu.lead_management.model.ProductHotelViewModel;
import com.jitu.lead_management.model.QuotationProductHotelModificationModel;
import com.jitu.lead_management.repository.ProductHotelRepository;
import com.jitu.lead_management.utils.ProductUtils;

@Service
public class ProductHotelServiceImpl implements ProductHotelService {

    @Autowired
    private ProductHotelRepository productHotelRepository;

    @Override
    public void create(ProductHotelModificationModel productHotelModel) {
        ProductHotel productHotel = new ProductHotel(productHotelModel);
        save(productHotel);
    }

    @Override
    public int create(QuotationProductHotelModificationModel quotationProductHotel) {
        // Calculate total hours between checkInDate and CheckOutDate
        long diffInMillies = Math
                .abs(quotationProductHotel.getCheckOutDate().getTime()
                        - quotationProductHotel.getCheckInDate().getTime());
        long totalHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        // Calculate hourly price from total price, number of rooms, and total hours
        double hourlyPrice = quotationProductHotel.getPrice() / (quotationProductHotel.getNoOfRooms() * totalHours);

        // Create ProductHotel from QuotationProductHotelModificationModel
        ProductHotel productHotel = new ProductHotel();
        productHotel.setName(quotationProductHotel.getName());
        productHotel.setRoomType(quotationProductHotel.getRoomType());
        productHotel.setHourlyPrice(hourlyPrice);

        productHotel = save(productHotel);
        return productHotel.getProductId();
    }

    @Override
    public ProductHotelViewModel findById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        ProductHotel productHotel = productHotelRepository.findById(intProductId)
                .orElseThrow(() -> new ProductHotelNotFoundException("Error: Product Hotel not found"));

        return new ProductHotelViewModel(productHotel);
    }

    @Override
    public List<ProductHotelViewModel> findAll() {
        List<ProductHotel> productHotelList = productHotelRepository.findAll();
        return productHotelList.stream().map(ProductHotelViewModel::new).collect(Collectors.toList());
    }

    @Override
    public List<String> fetchProductNames() {
        return productHotelRepository.fetchProductNames();
    }

    @Override
    public void update(String productId, ProductHotelModificationModel productHotelModel) {
        int intProductId = ProductUtils.resolveProductId(productId);

        ProductHotel productHotel = productHotelRepository.findById(intProductId)
                .orElseThrow(() -> new ProductHotelNotFoundException("Error: Product Hotel not found"));

        productHotel = ProductUtils.mapProductHotelUpdate(productHotel, productHotelModel);

        save(productHotel);
    }

    @Override
    public void deleteByIds(List<String> productIds) {
        List<Integer> intProductIds = productIds.stream().map(ProductUtils::resolveProductId)
                .collect(Collectors.toList());
        productHotelRepository.deleteByProductIdIn(intProductIds);
    }

    @Override
    public void deleteById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        productHotelRepository.deleteById(intProductId);
    }

    @Override
    public void deleteAll() {
        productHotelRepository.deleteAll();
    }

    private ProductHotel save(ProductHotel productHotel) {
        try {
            return productHotelRepository.save(productHotel);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Duplicate entry") || errorMessage.contains("uq_name_ph")) {
                throw new DuplicateProductNameException("Duplicate names are not allowed");
            } else {
                throw e;
            }
        }
    }
}
