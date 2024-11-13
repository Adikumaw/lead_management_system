package com.jitu.lead_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.Lead;
import com.jitu.lead_management.model.CreateLeadModel;
import com.jitu.lead_management.repository.LeadRepository;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private UserService userService;

    @Autowired
    private LeadRepository leadRepository;

    @Override
    public void createLead(String reference, CreateLeadModel requestLead) {
        int userId = userService.findUserIdByEmail(reference);

        Lead lead = new Lead(userId, requestLead);
        leadRepository.save(lead);
    }
}
