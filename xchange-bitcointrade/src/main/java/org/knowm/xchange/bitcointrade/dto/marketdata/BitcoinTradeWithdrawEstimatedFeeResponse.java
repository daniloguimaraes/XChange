package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#cd0b440a-109b-80b3-9df6-8f3a0b10e32f">BitcoinTrade
 *     API - Withdraw fee estimate Documentation (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "data"})
public class BitcoinTradeWithdrawEstimatedFeeResponse
    extends BitcoinTradeBaseResponse<List<BitcoinTradeWithdrawEstimatedFee>> {

  @JsonCreator
  public BitcoinTradeWithdrawEstimatedFeeResponse(
      @JsonProperty("message") Object message,
      @JsonProperty("data") List<BitcoinTradeWithdrawEstimatedFee> data) {

    super(message, data);
  }
}
