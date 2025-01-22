package com.jitu.lead_management.utils;

import java.util.stream.Collectors;

import com.jitu.lead_management.entity.Quotation;
import com.jitu.lead_management.entity.QuotationProductCar;
import com.jitu.lead_management.entity.QuotationProductHotel;
import com.jitu.lead_management.entity.QuotationProductPlace;
import com.jitu.lead_management.entity.QuotationProductPlane;
import com.jitu.lead_management.entity.QuotationProductRestaurant;
import com.jitu.lead_management.entity.QuotationProductTrain;
import com.jitu.lead_management.model.QuotationModificationModel;

public class QuotationUtils {
    public static String generateQuotationId(int quotationId, int userId) {
        return "QT-" + quotationId + "-U" + userId;
    }

    public static int resolveQuotationId(String quotationId) {
        return Integer.parseInt(quotationId.substring(3, quotationId.indexOf("-U")));
    }

    public static Quotation mapQuotationUpdate(Quotation quotation, QuotationModificationModel update) {
        quotation.setCurrency(update.getCurrency());
        quotation.setExecutive(update.getExecutive());
        quotation.setClientName(update.getClientName());
        quotation.setClientContactNo(update.getClientContactNo());
        quotation.setClientEmail(update.getClientEmail());
        quotation.setNoOfAdults(update.getNoOfAdults());
        quotation.setNoOfChildren(update.getNoOfChildren());
        quotation.setDestination(update.getDestination());
        quotation.setSubject(update.getSubject());
        quotation.setTripDetails(update.getTripDetails());
        quotation.setThankYouNote(update.getThankYouNote());
        quotation.setTermsAndConditions(update.getTermsAndConditions());
        quotation.setSubTotal(update.getSubTotal());
        quotation.setDiscount(update.getDiscount());
        quotation.setTotal(update.getTotal());
        quotation.setValidUpto(update.getValidUpto());
        quotation.setQuotationProductCars(update.getQuotationProductCarModels().stream().map(QuotationProductCar::new)
                .collect(Collectors.toList()));
        quotation.setQuotationProductHotels(update.getQuotationProductHotelModels().stream()
                .map(QuotationProductHotel::new).collect(Collectors.toList()));
        quotation.setQuotationProductPlaces(update.getQuotationProductPlaceModels().stream()
                .map(QuotationProductPlace::new).collect(Collectors.toList()));
        quotation.setQuotationProductPlanes(update.getQuotationProductPlaneModels().stream()
                .map(QuotationProductPlane::new).collect(Collectors.toList()));
        quotation.setQuotationProductRestaurants(update.getQuotationProductRestaurantModels().stream()
                .map(QuotationProductRestaurant::new).collect(Collectors.toList()));
        quotation.setQuotationProductTrains(update.getQuotationProductTrainModels().stream()
                .map(QuotationProductTrain::new).collect(Collectors.toList()));

        return quotation;
    }
}
