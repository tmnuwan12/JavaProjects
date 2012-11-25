/***
 **  @(#) TradeCard.com 1.0
 **
 **  Copyright (c) 2012 TradeCard, Inc. All Rights Reserved.
 **
 **
 **  THIS COMPUTER SOFTWARE IS THE PROPERTY OF TradeCard, Inc.
 **
 **  Permission is granted to use this software as specified by the TradeCard
 **  COMMERCIAL LICENSE AGREEMENT.  You may use this software only for
 **  commercial purposes, as specified in the details of the license.
 **  TRADECARD SHALL NOT BE LIABLE FOR ANY  DAMAGES SUFFERED BY
 **  THE LICENSEE AS A RESULT OF USING OR MODIFYING THIS SOFTWARE IN ANY WAY.
 **
 **  YOU MAY NOT DISTRIBUTE ANY SOURCE CODE OR OBJECT CODE FROM THE TradeCard.com
 **  TOOLKIT AT ANY TIME. VIOLATORS WILL BE PROSECUTED TO THE FULLEST EXTENT
 **  OF UNITED STATES LAW.
 **
 **  @version 1.0
 **  @author Copyright (c) 2012 TradeCard, Inc. All Rights Reserved.
 **
 **/
package com.org.tradecard.rest.test;

import java.net.*;
import org.junit.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.*;
import com.org.tradecard.domain.*;
import com.org.tradecard.status.*;

/**
 * @author tnuwan
 * Test class for testing RESTFul services.
 * 
 **/
//@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration("/spring-test-config.xml")
public class RestClientTest extends BaseTest{

   private static Logger log = LoggerFactory.getLogger(RestClientTest.class);

   private static String GET_URI = "http://localhost:8080/mywebapp/web/rest/service1";

   private static String POST_URI = "http://localhost:8080/mywebapp/web/rest/service2";

   private static int REQUEST_ID = 1;

   private static String MESSAGE_BODY = "Test Message";

   @Autowired() @Qualifier("restTemplate") private RestTemplate restTemplate;

   /**
    * Send GET request to the server.
    * Perform a basic null response check.
    * TODO: Extention for different response statues.
    */
   @Test
   public void testGetRequest() {
      URI requestUri;
      try {
         requestUri = new URI(GET_URI);

         HttpHeaders headers = new HttpHeaders();
         //Add more headers as required
         headers.setContentType(MediaType.APPLICATION_XML);
         HttpEntity<RequestStub> requestEntity = new HttpEntity<RequestStub>(null, headers);

         ResponseEntity<ResponseStub> responseEntity = restTemplate.exchange(GET_URI,HttpMethod.GET, requestEntity, ResponseStub.class);
         responseEntity.getStatusCode();
         responseEntity.getBody();
         //  restTemplate.getForObject(requestUri, ResponseStub.class);

         //Assert.assertNotNull(IMessage.ASSERT_NULL_ERROR_MSG, response);

      } catch (URISyntaxException e) {

         log.error("Error creating URI, make sure URI syntax is correnct" + "\n" + e);
      }

   }

   /**
    * Send POST request to the server.
    * Perform a basic null response check.
    * TODO: Extention for different response statues.
    */
   @Test public void testPostRequest() {
      URI requestUri;
      try {

         requestUri = new URI(POST_URI);
         RequestStub request = new RequestStub();
         request.setRequestId(REQUEST_ID);
         request.setMessage(MESSAGE_BODY);
         request.setStatus(StatusCode.OK);
         ResponseStub response = restTemplate.postForObject(requestUri, request, ResponseStub.class);
         Assert.assertNotNull(IMessage.ASSERT_NULL_ERROR_MSG, response);

      } catch (URISyntaxException e) {
         log.error("Error creating URI, make sure URI syntax is correnct" + "\n" + e);
      }
   }
}
