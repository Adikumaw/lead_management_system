package com.jitu.lead_management.utils;

import com.jitu.lead_management.entity.ProductCar;
import com.jitu.lead_management.entity.ProductHotel;
import com.jitu.lead_management.entity.ProductPlace;
import com.jitu.lead_management.entity.ProductRestaurant;
import com.jitu.lead_management.model.ProductCarModificationModel;
import com.jitu.lead_management.model.ProductHotelModificationModel;
import com.jitu.lead_management.model.ProductPlaceModificationModel;
import com.jitu.lead_management.model.ProductRestaurantModificationModel;

public class ProductUtils {
    public static String generateProductId(int productId) {
        return "PD-" + productId;
    }

    public static int resolveProductId(String productId) {
        return Integer.parseInt(productId.substring(3));
    }

    public static ProductCar mapProductCarUpdate(ProductCar productCar, ProductCarModificationModel productCarModel) {
        productCar.setName(productCarModel.getName());
        productCar.setHourlyPrice(productCarModel.getHourlyPrice());
        return productCar;
    }

    public static ProductHotel mapProductHotelUpdate(ProductHotel productHotel,
            ProductHotelModificationModel productHotelModel) {
        productHotel.setName(productHotelModel.getName());
        productHotel.setRoomType(productHotelModel.getRoomType());
        productHotel.setHourlyPrice(productHotelModel.getHourlyPrice());
        return productHotel;
    }

    public static ProductPlace mapProductPlaceUpdate(ProductPlace productPlace,
            ProductPlaceModificationModel productPlaceModel) {
        productPlace.setName(productPlaceModel.getName());
        productPlace.setPerChildPrice(productPlaceModel.getPerChildPrice());
        productPlace.setPerAdultPrice(productPlaceModel.getPerAdultPrice());
        return productPlace;
    }

    public static ProductRestaurant mapProductRestaurantUpdate(ProductRestaurant productRestaurant,
            ProductRestaurantModificationModel productRestaurantModel) {
        productRestaurant.setName(productRestaurantModel.getName());
        productRestaurant.setAvgPerChildPrice(productRestaurantModel.getAvgPerChildPrice());
        productRestaurant.setAvgPerAdultPrice(productRestaurantModel.getAvgPerAdultPrice());
        return productRestaurant;
    }
}
