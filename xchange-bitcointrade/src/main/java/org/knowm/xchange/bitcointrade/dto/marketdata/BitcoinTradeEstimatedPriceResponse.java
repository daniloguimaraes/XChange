package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#c3fbdb41-fdd6-108c-753d-5efcfeff7a7e">Bitcointrade
 *     API - Estimated Price Documentation (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "data"})
public class BitcoinTradeEstimatedPriceResponse
    extends BitcoinTradeBaseResponse<BitcoinTradeEstimatedPrice> {

  @JsonCreator
  public BitcoinTradeEstimatedPriceResponse(
      @JsonProperty("message") Object message,
      @JsonProperty("data") BitcoinTradeEstimatedPrice data) {

    super(message, data);
  }
}
