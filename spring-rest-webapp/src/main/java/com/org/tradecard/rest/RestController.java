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
package com.org.tradecard.rest;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.org.tradecard.domain.*;
import com.org.tradecard.status.*;

/**
 * @author nthusitha
 * Rest controller using spring mvc.
 **/
@Controller
@RequestMapping(value="/rest")
public class RestController
{
   private static Logger log = LoggerFactory.getLogger(RestController.class);
   
   public RestController()
   {

   }
   /**
    * Consumes application/json
    * @return TestStub
    */
   @RequestMapping(value="/service1", method=RequestMethod.GET, produces={"application/xml", "application/json"})
   @ResponseBody
   public ResponseStub serviceGetRequest()
   {
     
      //TODO: Implementation of service
      //TODO: Implement Query param filtering
      log.info("Restful GET Request Received");
      return new ResponseStub();
   }



   @RequestMapping(value="/service2", method=RequestMethod.POST, produces= {"application/xml", "application/json"})
   @ResponseBody
   public ResponseStub servicePostRequest(@RequestBody RequestStub request)
   {
      //TODO: Implementation of service, i.e.do somthing with the request
      RequestStub requestReceived = request;
      log.info("Received post request " + requestReceived.toString());
      ResponseStub response = new ResponseStub();
      Double randomId = Math.random() * 11;
      response.setResponseId( randomId.intValue()); //returns random Id for every request
      response.setResponse("Got you reqest succesfully"); //sets response message
      response.setStatus(StatusCode.OK);
      return response;

   }

   /*   @ExceptionHandler(Exception.class)
   public void handleUnsupportedMediaTypeException(Exception e, HttpServletRequest request)
   {

   }*/

   //TODO: Implement PUT & DELETE later

}
