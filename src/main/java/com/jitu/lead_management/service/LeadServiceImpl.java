package com.jitu.lead_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.Lead;
import com.jitu.lead_management.exception.LeadNotFoundException;
import com.jitu.lead_management.model.LeadModificationModel;
import com.jitu.lead_management.model.LeadViewModel;
import com.jitu.lead_management.repository.LeadRepository;
import com.jitu.lead_management.utils.LeadUtils;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private UserService userService;

    @Autowired
    private LeadRepository leadRepository;

    @Override
    public void createLead(String reference, LeadModificationModel requestLead) {
        int userId = userService.findUserIdByEmail(reference);

        Lead lead = new Lead(userId, requestLead);
        leadRepository.save(lead);
    }

    @Override
    public List<LeadViewModel> getLeads(int userId) {
        List<Lead> leads = leadRepository.findAllByUserId(userId);
        return leads.stream().map(LeadViewModel::new).collect(Collectors.toList());
    }

    @Override
    public List<LeadViewModel> getLeads(String reference) {
        int userId = userService.findUserIdByEmail(reference);

        List<Lead> leads = leadRepository.findAllByUserId(userId);
        return leads.stream().map(LeadViewModel::new).collect(Collectors.toList());
    }

    @Override
    public LeadViewModel getLeadById(String leadId, String reference) {
        int userId = userService.findUserIdByEmail(reference);

        int intLeadId = LeadUtils.resolveLeadId(leadId);
        Lead lead = leadRepository.findByLeadIdAndUserId(intLeadId, userId)
                .orElseThrow(() -> new LeadNotFoundException("Error: Lead not found"));

        return new LeadViewModel(lead);
    }

    @Override
    public void updateLead(String reference, String leadId, LeadModificationModel requestLead) {
        int userId = userService.findUserIdByEmail(reference);

        int intLeadId = LeadUtils.resolveLeadId(leadId);

        Lead lead = leadRepository.findByLeadIdAndUserId(intLeadId, userId)
                .orElseThrow(() -> new LeadNotFoundException("Error: lead not found"));

        lead = LeadUtils.mapLeadUpdate(lead, requestLead);

        leadRepository.save(lead);
    }

    @Override
    public void deleteLeadsByIds(List<String> leadIds, String reference) {
        int userId = userService.findUserIdByEmail(reference);

        // convert String of leadIds to original Integer lead IDs
        List<Integer> intLeadIds = leadIds.stream().map(LeadUtils::resolveLeadId).collect(Collectors.toList());

        leadRepository.deleteByLeadIdInAndUserId(intLeadIds, userId);
    }

    @Override
    public void deleteAllLeads(String reference) {
        int userId = userService.findUserIdByEmail(reference);

        leadRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteByLeadId(String leadId, String reference) {
        int userId = userService.findUserIdByEmail(reference);

        int intLeadId = LeadUtils.resolveLeadId(leadId);

        leadRepository.deleteByLeadIdAndUserId(intLeadId, userId);
    }

}
