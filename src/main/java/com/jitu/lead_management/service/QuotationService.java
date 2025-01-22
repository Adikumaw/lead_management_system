package com.jitu.lead_management.service;

import java.util.List;

import com.jitu.lead_management.model.QuotationModificationModel;
import com.jitu.lead_management.model.QuotationViewModel;

public interface QuotationService {

    void create(String reference, QuotationModificationModel quotationModel);

    QuotationViewModel findById(String quotationId);

    List<QuotationViewModel> findAll();

    void update(String quotationId, QuotationModificationModel quotationModel);

    void deleteByIds(List<String> quotationIds);

}
