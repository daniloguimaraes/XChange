package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;

/**
 * BitcoinTrade Wallet Balance response.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#operation/GetUserWallets">BitcoinTrade API - *
 *     Wallets Balance</a>
 */
public class BitcoinTradeWalletBalanceResponse
    extends BitcoinTradeBaseResponse<Collection<BitcoinTradeWalletBalance>> {

  @JsonCreator
  public BitcoinTradeWalletBalanceResponse(
      @JsonProperty("message") Object message,
      @JsonProperty("data") Collection<BitcoinTradeWalletBalance> data) {
    super(message, data);
  }


}
