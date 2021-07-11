package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.BitcoinTradeDepositStatus;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#34810ae9-69e0-8c83-5c61-01d81162be10">Bitcointrade
 *     API - Deposit List Documentation (Brazilian Portuguese)</a>
 */
public class BitcoinTradeDeposit {

  private final String code;
  private final String hash;
  private final BigDecimal amount;
  private final BitcoinTradeDepositStatus status;
  private final String createDate;
  private final String confirmationDate;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonCreator
  public BitcoinTradeDeposit(
      @JsonProperty("code") String code,
      @JsonProperty("hash") String hash,
      @JsonProperty("amount") BigDecimal amount,
      @JsonProperty("status") String status,
      @JsonProperty("create_date") String createDate,
      @JsonProperty("confirmation_date") String confirmationDate) {

    this.code = code;
    this.hash = hash;
    this.amount = amount;
    this.status = BitcoinTradeDepositStatus.from(status);
    this.createDate = createDate;
    this.confirmationDate = confirmationDate;
  }

  public String getCode() {

    return code;
  }

  public String getHash() {

    return hash;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public BitcoinTradeDepositStatus getStatus() {

    return status;
  }

  public String getCreateDate() {

    return createDate;
  }

  public String getConfirmationDate() {

    return confirmationDate;
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
        .append("code", code)
        .append("hash", hash)
        .append("amount", amount)
        .append("status", status)
        .append("createDate", createDate)
        .append("confirmationDate", confirmationDate)
        .append("additionalProperties", additionalProperties)
        .toString();
  }
}
