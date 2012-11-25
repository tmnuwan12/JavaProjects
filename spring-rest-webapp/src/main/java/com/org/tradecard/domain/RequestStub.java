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
import com.org.tradecard.namespace.*;
import com.org.tradecard.status.*;

/**
 * @author nthusitha
 * RequestStub class for mapping to and from HTTP request to Javaobject .
 **/
@XmlRootElement(name="RequestStub", namespace = Namespace.REQUEST_NAMESPACE)
//@XmlType(namespace = Namespace.REQUEST_NAMESPACE)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequestStub {

   private int requestId;

   private String message;

   private StatusCode status;

   @XmlElement(required = true)
   public int getRequestId() {
      return requestId;
   }


   public void setRequestId(int requestId) {
      this.requestId = requestId;
   }
   @XmlElement(required = true)
   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
   @XmlElement(required = true)
   public StatusCode getStatus() {
      return status;
   }

   public void setStatus(StatusCode status) {
      this.status = status;
   }


   @Override public String toString() {
      return "RequestStub [requestId=" + requestId + ", message=" + message + ", status=" + status + "]";
   }


}
