package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#9a20d5e9-056b-7427-5f22-35f571f60411">Bitcointrade
 *     API - Summary Documentation (Brazilian Portuguese)</a>
 */
public class BitcointradeSummaryResponse extends BitcointradeBaseResponse<BitcointradeSummary> {

  /**
   * @param message
   * @param data
   */
  @JsonCreator
  public BitcointradeSummaryResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") BitcointradeSummary data) {

    super(message, data);
  }
}
