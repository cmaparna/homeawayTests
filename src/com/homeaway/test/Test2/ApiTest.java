package com.homeaway.test.Test2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.testng.annotations.Test;

public class ApiTest {
	
	@Test
	public void GetJSON_response() throws ClientProtocolException, IOException{

	   HttpUriRequest request = new HttpGet( "https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key=ppEdQzmBM66AZBmZQiHVgWMepCT9iLUCJn9Gu8M5&location=Denver+CO" );
	   
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	   Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
	  
	}
	
	
	
	
	
}
