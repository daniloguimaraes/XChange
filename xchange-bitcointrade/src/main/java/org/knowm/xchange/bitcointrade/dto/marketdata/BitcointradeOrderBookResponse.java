package org.knowm.xchange.bitcointrade.dto.marketdata;

import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bitcointrade Exchange Order Book response representation.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#dc3695f5-6129-e35c-153d-c629aee8fd48">Bitcointrade API - Trades Documentation
 * (Brazilian Portuguese)</a>
 */
public class BitcointradeOrderBookResponse extends BitcointradeBaseResponse<BitcointradeOrderBook> {

  /**
   * Constructor
   *
   * @param message the message, in case of error
   * @param data the order book data
   */
  @JsonCreator
  public BitcointradeOrderBookResponse(@JsonProperty("message") Object message, @JsonProperty("data") BitcointradeOrderBook data) {

    super(message, data);
  }

}
