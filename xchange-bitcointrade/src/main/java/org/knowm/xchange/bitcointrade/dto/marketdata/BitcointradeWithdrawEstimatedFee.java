package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.BitcointradeFeeType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Danilo Guimaraes
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "amount" })
public class BitcointradeWithdrawEstimatedFee {

  private final BitcointradeFeeType name;
  private final String nameAsString;
  private final BigDecimal amount;

  @JsonCreator
  public BitcointradeWithdrawEstimatedFee(@JsonProperty("name") String name, @JsonProperty("amount") BigDecimal amount) {

    super();
    this.name = BitcointradeFeeType.from(name);
    this.nameAsString = name;
    this.amount = amount;
  }

  public BitcointradeFeeType getName() {

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
