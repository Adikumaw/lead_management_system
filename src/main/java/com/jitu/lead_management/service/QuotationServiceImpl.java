package com.jitu.lead_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.Quotation;
import com.jitu.lead_management.model.QuotationModificationModel;
import com.jitu.lead_management.model.QuotationProductCarModificationModel;
import com.jitu.lead_management.model.QuotationProductHotelModificationModel;
import com.jitu.lead_management.model.QuotationProductPlaceModificationModel;
import com.jitu.lead_management.model.QuotationProductRestaurantModificationModel;
import com.jitu.lead_management.repository.QuotationRepository;
import com.jitu.lead_management.utils.ProductUtils;

@Service
public class QuotationServiceImpl implements QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductCarService productCarService;
    @Autowired
    private ProductHotelService productHotelService;
    @Autowired
    private ProductPlaceService productPlaceService;
    @Autowired
    private ProductRestaurantService productRestaurantService;

    @Override
    public void create(String reference, QuotationModificationModel quotationModel) {
        int userId = userService.findUserIdByEmail(reference);

        // check for non id products for all products except plane and train and create
        // product first before creating quotation.
        for (QuotationProductCarModificationModel quotationProductCar : quotationModel.getQuotationProductCarModels()) {
            if (quotationProductCar.getId() == null || quotationProductCar.getId().isEmpty()) {
                int id = productCarService.create(quotationProductCar);
                quotationProductCar.setId(ProductUtils.generateProductId(id));
            }
        }
        for (QuotationProductHotelModificationModel quotationProductHotel : quotationModel
                .getQuotationProductHotelModels()) {
            if (quotationProductHotel.getId() == null || quotationProductHotel.getId().isEmpty()) {
                int id = productHotelService.create(quotationProductHotel);
                quotationProductHotel.setId(ProductUtils.generateProductId(id));
            }
        }
        for (QuotationProductPlaceModificationModel quotationProductPlace : quotationModel
                .getQuotationProductPlaceModels()) {
            if (quotationProductPlace.getId() == null || quotationProductPlace.getId().isEmpty()) {
                int id = productPlaceService.create(quotationProductPlace);
                quotationProductPlace.setId(ProductUtils.generateProductId(id));
            }
        }
        for (QuotationProductRestaurantModificationModel quotationProductRestaurant : quotationModel
                .getQuotationProductRestaurantModels()) {
            if (quotationProductRestaurant.getId() == null || quotationProductRestaurant.getId().isEmpty()) {
                int id = productRestaurantService.create(quotationProductRestaurant);
                quotationProductRestaurant.setId(ProductUtils.generateProductId(id));
            }
        }

        Quotation quotation = new Quotation(userId, quotationModel);

        quotationRepository.save(quotation);
    }
}
