package com.krishna.services.util;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

import com.krishna.service.model.TestService;

/**
 * Class to manage getting Payload. This will be very useful if we want to test with multiple combinations of payload
 * in one test script file.
 * 
 * @author kabothu 
 */


public class PayLoad {
	
	private static final ObjectMapper JSONMapper = new ObjectMapper();
	
	public  String populatePayLoad(TestService testService)  {
		PayLoadBuilder payloadBuilder = new PayLoadBuilder.Builder()
				.title(testService.getTitle())
				.description(testService.getDescription())
				.name(testService.getName())
				.poster_path(testService.getPoster_path())
				.build();
		String payload;
		try {
			payload = JSONMapper.writeValueAsString(payloadBuilder);
			return payload;
		} catch (IOException e) {
			e.getMessage();
		}
		return null;
	}

}
