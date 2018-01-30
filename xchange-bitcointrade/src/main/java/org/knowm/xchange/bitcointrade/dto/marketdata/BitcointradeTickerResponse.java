package org.knowm.xchange.bitcointrade.dto.marketdata;

import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bitcointrade Exchange Ticker response representation
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#8e6f6b73-b2f8-c03a-9d60-a0159f2c6ce0">Bitcointrade API - Ticker Documentation
 * (Brazilian Portuguese)</a>
 */
public class BitcointradeTickerResponse extends BitcointradeBaseResponse<BitcointradeTicker> {

  /**
   * Constructor
   *
   * @param message the message, in case of error
   * @param ticker the data containing the ticker itself.
   */
  @JsonCreator
  public BitcointradeTickerResponse(@JsonProperty("message") Object message, @JsonProperty("data") BitcointradeTicker ticker) {

    super(message, ticker);
  }

}
