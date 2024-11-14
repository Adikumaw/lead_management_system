package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.CreateLeadModel;
import com.jitu.lead_management.model.LeadViewModel;

public interface LeadService {

    void createLead(String reference, CreateLeadModel requestLead);

    List<LeadViewModel> getLeads(String reference);

    List<LeadViewModel> getLeads(int userId);

    LeadViewModel getLeadById(String leadId, String reference);

    List<LeadViewModel> deleteLeadsByIds(List<String> leadIds, String reference);

    List<LeadViewModel> deleteAllLeads(String reference);

    List<LeadViewModel> deleteByLeadId(String leadId, String reference);
}
