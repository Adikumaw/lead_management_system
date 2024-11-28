package com.jitu.lead_management.utils;

import java.util.Date;

import com.jitu.lead_management.entity.Lead;
import com.jitu.lead_management.model.LeadModificationModel;

public class LeadUtils {
    public static String generateLeadId(int leadId, int userId) {
        return "LD-" + leadId + "-U" + userId;
    }

    public static int resolveLeadId(String leadId) {
        return Integer.parseInt(leadId.substring(3, leadId.indexOf("-U")));
    }

    public static Lead mapLeadUpdate(Lead lead, LeadModificationModel update) {
        lead.setStage(update.getStage());
        lead.setClientName(update.getClientName());
        lead.setClientContactNo(update.getClientContactNo());
        lead.setClientEmailId(update.getClientEmailId());
        lead.setFollowUp(update.getFollowUp());
        lead.setExecutive(update.getExecutive());
        lead.setStatus(update.getStatus());
        lead.setEnquiryType(update.getEnquiryType());
        lead.setPackageName(update.getPackageName());
        lead.setPlannedTravelDate(update.getPlannedTravelDate());
        lead.setDestination(update.getDestination());
        lead.setNoOfAdults(update.getNoOfAdults());
        lead.setBudgetPerAdult(update.getBudgetPerAdult());
        lead.setNoOfChildren(update.getNoOfChildren());
        lead.setBudgetPerChild(update.getBudgetPerChild());
        // update lead update time
        lead.setUpdatedAt(new Date());

        return lead;
    }
}
