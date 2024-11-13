package com.jitu.lead_management.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Adjust the date and format it as required
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        gen.writeString(sdf.format(date));
    }
}
