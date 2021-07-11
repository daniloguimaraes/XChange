package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;

/** @author Danilo Guimaraes */
public class BitcoinTradeWithdrawResponse extends BitcoinTradeBaseResponse<BitcoinTradeWithdraw> {

  @JsonCreator
  public BitcoinTradeWithdrawResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") BitcoinTradeWithdraw data) {

    super(message, data);
  }
}
