package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.util.List;

import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#cd0b440a-109b-80b3-9df6-8f3a0b10e32f">Bitcointrade API - Withdraw fee estimate Documentation
 * (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "data" })
public class BitcointradeWithdrawEstimatedFeeResponse extends BitcointradeBaseResponse<List<BitcointradeWithdrawEstimatedFee>> {

  @JsonCreator
  public BitcointradeWithdrawEstimatedFeeResponse(@JsonProperty("message") Object message,
      @JsonProperty("data") List<BitcointradeWithdrawEstimatedFee> data) {

    super(message, data);
  }

}
