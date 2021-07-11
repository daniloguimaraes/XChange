package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#9a20d5e9-056b-7427-5f22-35f571f60411">BitcoinTrade
 *     API - Summary Documentation (Brazilian Portuguese)</a>
 */
public class BitcoinTradeSummaryResponse extends BitcoinTradeBaseResponse<BitcoinTradeSummary> {

  /**
   * @param message
   * @param data
   */
  @JsonCreator
  public BitcoinTradeSummaryResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") BitcoinTradeSummary data) {

    super(message, data);
  }
}
