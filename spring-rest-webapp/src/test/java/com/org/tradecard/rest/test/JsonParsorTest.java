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

import net.sf.json.*;
import org.junit.*;
import com.org.tradecard.domain.*;

/**
 * @author nthusitha
 * Test class for Json parsor.
 **/
public class JsonParsorTest extends BaseTest{


   /**
    * Dummy test to check string representation of
    * Java object converted to Json.
    */
   @Test
   public void jsonParsorTeset()
   {
      JSONObject jsonObject = JSONObject.fromObject(new RequestStub());
      System.out.println(jsonObject);
      /*
       * prints
       * {"message":"","requestId":0,"status":null}
       * 
       * make sure you have values are bounded with "" characters
       * {"message":"","requestId":"0","status":"OK"} to avoid
       * requst not syntactically formed errors
       */
   }
}
