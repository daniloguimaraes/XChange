package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.util.List;

import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Danilo Guimaraes
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
