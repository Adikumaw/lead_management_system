package com.jitu.lead_management.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jitu.lead_management.model.QuotationModificationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotations")
public class Quotation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "quotation_id")
        private int quotationId;
        @Column(name = "user_id")
        private int userId;
        @Column(name = "currency")
        private String currency;
        @Column(name = "executive")
        private String executive;
        @Column(name = "client_name")
        private String clientName;
        @Column(name = "client_contact_no")
        private String clientContactNo;
        @Column(name = "client_email_id")
        private String clientEmail;
        @Column(name = "no_of_adults")
        private int noOfAdults;
        @Column(name = "no_of_children")
        private int noOfChildren;
        @Column(name = "destination")
        private String destination;
        @Column(name = "subject")
        private String subject;
        @Column(name = "trip_details")
        private String tripDetails;
        @Column(name = "thank_you_note")
        private String thankYouNote;
        @Column(name = "terms_and_conditions")
        private String termsAndConditions;

        @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
        private List<QuotationProductCar> quotationProductCars;

        @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
        private List<QuotationProductTrain> quotationProductTrains;

        @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
        private List<QuotationProductPlane> quotationProductPlanes;

        @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
        private List<QuotationProductHotel> quotationProductHotels;

        @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
        private List<QuotationProductRestaurant> quotationProductRestaurants;

        @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
        private List<QuotationProductPlace> quotationProductPlaces;

        @Column(name = "sub_total")
        private double subTotal;
        @Column(name = "discount")
        private double discount;
        @Column(name = "total")
        private double total;
        @Column(name = "created_at")
        private Date createdAt;
        @Column(name = "valid_upto")
        private Date validUpto;

        public Quotation(int userId, QuotationModificationModel quotationModel) {
                this.userId = userId;
                this.currency = quotationModel.getCurrency();
                this.executive = quotationModel.getExecutive();
                this.clientName = quotationModel.getClientName();
                this.clientContactNo = quotationModel.getClientContactNo();
                this.clientEmail = quotationModel.getClientEmail();
                this.noOfAdults = quotationModel.getNoOfAdults();
                this.noOfChildren = quotationModel.getNoOfChildren();
                this.destination = quotationModel.getDestination();
                this.subject = quotationModel.getSubject();
                this.tripDetails = quotationModel.getTripDetails();
                this.thankYouNote = quotationModel.getThankYouNote();
                this.termsAndConditions = quotationModel.getTermsAndConditions();
                this.subTotal = quotationModel.getSubTotal();
                this.discount = quotationModel.getDiscount();
                this.total = quotationModel.getTotal();
                this.createdAt = new Date();
                this.validUpto = quotationModel.getValidUpto();
                // use the setters to set the product lists
                setQuotationProductCars(quotationModel.getQuotationProductCarModels().stream()
                                .map(QuotationProductCar::new).collect(Collectors.toList()));
                setQuotationProductTrains(quotationModel.getQuotationProductTrainModels().stream()
                                .map(QuotationProductTrain::new).collect(Collectors.toList()));
                setQuotationProductPlanes(quotationModel.getQuotationProductPlaneModels().stream()
                                .map(QuotationProductPlane::new).collect(Collectors.toList()));
                setQuotationProductHotels(quotationModel.getQuotationProductHotelModels().stream()
                                .map(QuotationProductHotel::new).collect(Collectors.toList()));
                setQuotationProductRestaurants(quotationModel.getQuotationProductRestaurantModels().stream()
                                .map(QuotationProductRestaurant::new).collect(Collectors.toList()));
                setQuotationProductPlaces(quotationModel.getQuotationProductPlaceModels().stream()
                                .map(QuotationProductPlace::new).collect(Collectors.toList()));
        }

        // create setters for product lists
        public void setQuotationProductCars(List<QuotationProductCar> quotationProductCars) {
                this.quotationProductCars = quotationProductCars;
                quotationProductCars.forEach(quotationProductCar -> quotationProductCar.setQuotation(this));
        }

        public void setQuotationProductTrains(List<QuotationProductTrain> quotationProductTrains) {
                this.quotationProductTrains = quotationProductTrains;
                quotationProductTrains.forEach(quotationProductTrain -> quotationProductTrain.setQuotation(this));
        }

        public void setQuotationProductPlanes(List<QuotationProductPlane> quotationProductPlanes) {
                this.quotationProductPlanes = quotationProductPlanes;
                quotationProductPlanes.forEach(quotationProductPlane -> quotationProductPlane.setQuotation(this));
        }

        public void setQuotationProductHotels(List<QuotationProductHotel> quotationProductHotels) {
                this.quotationProductHotels = quotationProductHotels;
                quotationProductHotels.forEach(quotationProductHotel -> quotationProductHotel.setQuotation(this));
        }

        public void setQuotationProductRestaurants(List<QuotationProductRestaurant> quotationProductRestaurants) {
                this.quotationProductRestaurants = quotationProductRestaurants;
                quotationProductRestaurants
                                .forEach(quotationProductRestaurant -> quotationProductRestaurant.setQuotation(this));
        }

        public void setQuotationProductPlaces(List<QuotationProductPlace> quotationProductPlaces) {
                this.quotationProductPlaces = quotationProductPlaces;
                quotationProductPlaces.forEach(quotationProductPlace -> quotationProductPlace.setQuotation(this));
        }
}
