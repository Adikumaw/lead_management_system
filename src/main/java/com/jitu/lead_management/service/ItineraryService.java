package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.ItineraryModificationModel;
import com.jitu.lead_management.model.ItineraryViewModel;

public interface ItineraryService {

    void createItinerary(ItineraryModificationModel itineraryModel);

    ItineraryViewModel findById(String itineraryId);

    List<ItineraryViewModel> findAll();

    void update(String itineraryId, ItineraryModificationModel itineraryModel);

    void deleteByIds(List<String> itineraryIds);

    void deleteById(String id);

    void deleteAll();

}
