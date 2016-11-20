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

/**
 * This Test class provides test cases for SOAP API test. 
 * 
 * Created date <19-Nov-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

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
	public void mockTest() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<sam:listCatalogueRequest xmlns:sam='" + APIPathAdvice.NAME_SPACE + "'>");
		sb.append("<sam:requestBody></sam:requestBody>");
		sb.append("</sam:listCatalogueRequest>");

		try {
	   
			mockClient.sendRequest(withPayload(new StringSource(sb.toString())));
			//.andExpect(payload(new StringSource(response)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
