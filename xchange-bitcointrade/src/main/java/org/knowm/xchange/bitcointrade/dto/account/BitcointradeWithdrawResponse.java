package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;

/** @author Danilo Guimaraes */
public class BitcointradeWithdrawResponse extends BitcointradeBaseResponse<BitcointradeWithdraw> {

  @JsonCreator
  public BitcointradeWithdrawResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") BitcointradeWithdraw data) {

    super(message, data);
  }
}
