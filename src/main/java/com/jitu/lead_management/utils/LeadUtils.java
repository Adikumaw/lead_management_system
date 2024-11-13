package com.jitu.lead_management.utils;

public class LeadUtils {
    public static String generateLeadId(int leadId, int userId) {
        return "LD-" + leadId + "-U" + userId;
    }

    public static int resolveLeadId(String leadId) {
        return Integer.parseInt(leadId.substring(3, leadId.indexOf("-U")));
    }
}
