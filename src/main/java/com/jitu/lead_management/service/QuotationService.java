package com.jitu.lead_management.service;

import com.jitu.lead_management.model.QuotationModificationModel;

public interface QuotationService {

    void create(String reference, QuotationModificationModel quotationModel);

}
