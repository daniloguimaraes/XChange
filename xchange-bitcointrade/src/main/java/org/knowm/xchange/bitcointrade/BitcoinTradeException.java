package org.knowm.xchange.bitcointrade;

import com.fasterxml.jackson.annotation.JsonProperty;

/** @author Danilo Guimaraes */
@SuppressWarnings("serial")
public class BitcoinTradeException extends RuntimeException {

  @JsonProperty("message")
  final String message;

  public BitcoinTradeException(@JsonProperty("message") String message) {

    super();
    this.message = message;
  }

  public String getError() {

    return message;
  }
}
