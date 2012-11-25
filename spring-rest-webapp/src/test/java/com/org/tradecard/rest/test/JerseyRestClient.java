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

import java.util.*;
import javax.ws.rs.core.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;

/**
 * @author nthusitha
 * Jersey rest client.
 **/

public class JerseyRestClient extends BaseTest{

   /**
    * 
    */
   @Test
   public void testGetRequest()
   {
   // Client client = Client.create();
    
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 60000);
    clientConfig.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
    
    Client client = Client.create(clientConfig);
    
    WebResource resource = client.resource("http://localhost:8080/mywebapp/web/rest/service1");
    
  //  ResponseStub response = resource.accept(MediaType.APPLICATION_XML).header("Authorisation", "test").post(ResponseStub.class, null);
    
    com.org.tradecard.domain.ResponseStub response = resource.accept(MediaType.APPLICATION_XML).get(com.org.tradecard.domain.ResponseStub.class);
    
    //To retrieve metadata from response
    ClientResponse clientResponse = resource.get(ClientResponse.class);
    EntityTag e = clientResponse.getEntityTag();
    //this will give access to header information
    MultivaluedMap<String, String> headers =  clientResponse.getHeaders(); //each key can have zero or more values
    
    Assert.assertNotNull(IMessage.ASSERT_NULL_ERROR_MSG, response); //fail the test if response is null
   }
   
}
