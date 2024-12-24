package com.jitu.lead_management.utils;

import com.jitu.lead_management.entity.Itinerary;
import com.jitu.lead_management.model.ItineraryModificationModel;

public class ItineraryUtils {

    public static String generateItineraryId(int ItineraryId) {
        return "IT-" + ItineraryId;
    }

    public static int resolveItineraryId(String ItineraryId) {
        return Integer.parseInt(ItineraryId.substring(3));
    }

    public static Itinerary mapItineraryUpdate(Itinerary itinerary, ItineraryModificationModel update) {
        itinerary.setTemplateName(update.getTemplateName());
        itinerary.setSubject(update.getSubject());
        itinerary.setTripDetails(update.getTripDetails());
        itinerary.setThankYouNote(update.getThankYouNote());
        itinerary.setTermsAndConditions(update.getTermsAndConditions());

        return itinerary;
    }
}
