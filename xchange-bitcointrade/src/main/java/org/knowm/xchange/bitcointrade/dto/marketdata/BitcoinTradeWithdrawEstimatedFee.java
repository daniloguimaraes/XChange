package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.BitcoinTradeFeeType;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#cd0b440a-109b-80b3-9df6-8f3a0b10e32f">BitcoinTrade
 *     API - Withdraw fee estimate Documentation (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "amount"})
public class BitcoinTradeWithdrawEstimatedFee {

  private final BitcoinTradeFeeType name;
  private final String nameAsString;
  private final BigDecimal amount;

  @JsonCreator
  public BitcoinTradeWithdrawEstimatedFee(
      @JsonProperty("name") String name, @JsonProperty("amount") BigDecimal amount) {

    super();
    this.name = BitcoinTradeFeeType.from(name);
    this.nameAsString = name;
    this.amount = amount;
  }

  public BitcoinTradeFeeType getName() {

    return name;
  }

  public String getNameAsString() {
    return nameAsString;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  @Override
  public String toString() {

    return new ToStringBuilder(this).append("name", name).append("amount", amount).toString();
  }
}
