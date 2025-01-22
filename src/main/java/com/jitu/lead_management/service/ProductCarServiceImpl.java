package com.jitu.lead_management.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.ProductCar;
import com.jitu.lead_management.exception.DuplicateProductNameException;
import com.jitu.lead_management.exception.ProductCarNotFoundException;
import com.jitu.lead_management.model.ProductCarModificationModel;
import com.jitu.lead_management.model.ProductCarViewModel;
import com.jitu.lead_management.model.QuotationProductCarModificationModel;
import com.jitu.lead_management.repository.ProductCarRepository;
import com.jitu.lead_management.utils.ProductUtils;

@Service
public class ProductCarServiceImpl implements ProductCarService {

    @Autowired
    private ProductCarRepository productCarRepository;

    @Override
    public void create(ProductCarModificationModel productCarModel) {
        ProductCar productCar = new ProductCar(productCarModel);
        save(productCar);
    }

    @Override
    public int create(QuotationProductCarModificationModel quotationProductCar) {
        // Calculate total hours between fromDate and toDate
        long diffInMillies = Math
                .abs(quotationProductCar.getToDate().getTime() - quotationProductCar.getFromDate().getTime());
        long totalHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        // Calculate hourly price from total price, number of cars, and total hours
        double hourlyPrice = quotationProductCar.getPrice() / (quotationProductCar.getNoOfCars() * totalHours);

        // Create ProductCar from QuotationProductCarModificationModel
        ProductCar productCar = new ProductCar();
        productCar.setName(quotationProductCar.getName());
        productCar.setHourlyPrice(hourlyPrice);

        productCar = save(productCar);
        return productCar.getProductId();
    }

    @Override
    public ProductCarViewModel findById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        ProductCar productCar = productCarRepository.findById(intProductId)
                .orElseThrow(() -> new ProductCarNotFoundException("Error: Product Car not found"));

        return new ProductCarViewModel(productCar);
    }

    @Override
    public List<ProductCarViewModel> findAll() {
        List<ProductCar> productCarList = productCarRepository.findAll();
        return productCarList.stream().map(ProductCarViewModel::new).collect(Collectors.toList());
    }

    @Override
    public List<String> fetchProductNames() {
        return productCarRepository.fetchProductNames();
    }

    @Override
    public void update(String productId, ProductCarModificationModel productCarModel) {
        int intProductId = ProductUtils.resolveProductId(productId);

        ProductCar productCar = productCarRepository.findById(intProductId)
                .orElseThrow(() -> new ProductCarNotFoundException("Error: Product Car not found"));

        productCar = ProductUtils.mapProductCarUpdate(productCar, productCarModel);

        save(productCar);
    }

    @Override
    public void deleteByIds(List<String> productIds) {
        List<Integer> intProductIds = productIds.stream().map(ProductUtils::resolveProductId)
                .collect(Collectors.toList());
        productCarRepository.deleteByProductIdIn(intProductIds);
    }

    @Override
    public void deleteById(String productId) {
        int intProductId = ProductUtils.resolveProductId(productId);
        productCarRepository.deleteById(intProductId);
    }

    @Override
    public void deleteAll() {
        productCarRepository.deleteAll();
    }

    private ProductCar save(ProductCar productCar) {
        try {
            return productCarRepository.save(productCar);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Duplicate entry") || errorMessage.contains("uq_name_pc")) {
                throw new DuplicateProductNameException("Duplicate names are not allowed");
            } else {
                throw e;
            }
        }
    }
}
