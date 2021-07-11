package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;

/**
 * BitcoinTrade Exchange Order Book response representation.
 *
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#dc3695f5-6129-e35c-153d-c629aee8fd48">BitcoinTrade
 *     API - Trades Documentation (Brazilian Portuguese)</a>
 */
public class BitcoinTradeOrderBookResponse extends BitcoinTradeBaseResponse<BitcoinTradeOrderBook> {

  /**
   * Constructor
   *
   * @param message the message, in case of error
   * @param data the order book data
   */
  @JsonCreator
  public BitcoinTradeOrderBookResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") BitcoinTradeOrderBook data) {

    super(message, data);
  }
}
