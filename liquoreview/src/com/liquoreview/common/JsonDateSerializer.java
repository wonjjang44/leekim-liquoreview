package com.liquoreview.common;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<Timestamp>{

	@Override
	public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		
	}

}
