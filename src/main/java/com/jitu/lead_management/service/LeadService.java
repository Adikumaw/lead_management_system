package com.jitu.lead_management.service;

import java.util.Date;

import com.jitu.lead_management.model.CreateLeadModel;

public interface LeadService {

    void createLead(String reference, CreateLeadModel requestLead);
}
