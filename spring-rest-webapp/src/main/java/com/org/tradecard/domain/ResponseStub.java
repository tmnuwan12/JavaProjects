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
package com.org.tradecard.domain;

import javax.xml.bind.annotation.*;
import com.org.tradecard.namespace.Namespace;
import com.org.tradecard.status.*;

/**
 * @author nthusitha
 * ResponseStub class for mapping to and from HTTP request to Java object.
 **/
@XmlRootElement(name="ResponseStub", namespace = Namespace.RESPONSE_NAMESPACE)
//@XmlType(namespace = Namespace.RESPONSE_NAMESPACE)
@XmlAccessorType(XmlAccessType.PROPERTY) public class ResponseStub {

   private int responseId;

   private String response; //extend this to complex type

   private StatusCode status;

   @XmlElement(required = true) public int getResponseId() {
      return responseId;
   }

   public void setResponseId(int responseId) {
      this.responseId = responseId;
   }

   @XmlElement(required = true) public String getResponse() {
      return response;
   }

   public void setResponse(String response) {
      this.response = response;
   }

   @XmlElement(required = true) public StatusCode getStatus() {
      return status;
   }

   public void setStatus(StatusCode status) {
      this.status = status;
   }

   @Override public String toString() {
      return "ResponseStub [responseId=" + responseId + ", response=" + response + ", status=" + status + "]";
   }

}
