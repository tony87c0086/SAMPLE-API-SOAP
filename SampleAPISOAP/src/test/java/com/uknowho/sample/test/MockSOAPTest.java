package com.uknowho.sample.test;

import static org.springframework.ws.test.server.RequestCreators.withPayload;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import com.uknowho.sample.soap.config.APIPathAdvice;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockSOAPTest extends TestCasePreload {

	@Autowired
	private ApplicationContext applicationContext;
	
	private MockWebServiceClient mockClient;
	
	@Before
	public void createWSClient() {
		Assert.notNull(applicationContext);
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}
	
	@Test
	public void listProductSummaryTest() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<run:listCatalogueRequest xmlns:run='" + APIPathAdvice.NAME_SPACE + "'>");
		sb.append("<run:requestBody></run:requestBody>");
		sb.append("</run:listCatalogueRequest>");

		try {
	   
			mockClient.sendRequest(withPayload(new StringSource(sb.toString())));
			//.andExpect(payload(new StringSource(response)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
