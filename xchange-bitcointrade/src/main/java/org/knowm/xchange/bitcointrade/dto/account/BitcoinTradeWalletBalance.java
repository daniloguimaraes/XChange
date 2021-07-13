package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.currency.Currency;

/**
 * BitcoinTrade Wallet Balance.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#operation/GetUserWallets">BitcoinTrade API -
 *      Wallets Balance</a>
 */
public class BitcoinTradeWalletBalance {

  private final BigDecimal availableAmount;
  private final Currency currencyCode;
  private final BigDecimal lockedAmount;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  public BitcoinTradeWalletBalance(
      @JsonProperty("available_amount") BigDecimal availableAmount,
      @JsonProperty("currency_code") Currency currencyCode,
      @JsonProperty("locked_amount") BigDecimal lockedAmount) {
    this.availableAmount = availableAmount;
    this.currencyCode = currencyCode;
    this.lockedAmount = lockedAmount;
  }

  public BigDecimal getAvailableAmount() {

    return availableAmount;
  }

  public Currency getCurrencyCode() {

    return currencyCode;
  }

  public BigDecimal getLockedAmount() {

    return lockedAmount;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {

    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {

    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("availableAmount", availableAmount)
        .append("currencyCode", currencyCode)
        .append("lockedAmount", lockedAmount)
        .append("additionalProperties", additionalProperties)
        .toString();
  }
}
