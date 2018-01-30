package org.knowm.xchange.bitcointrade.dto.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.BitcointradeFeeType;
import org.knowm.xchange.bitcointrade.BitcointradeWithdrawStatus;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bitcointrade Exchange Withdraw representation.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#2f4b6643-ae82-d9a3-8cb9-d025e92982fe">Bitcointrade API - Withdraw List Documentation
 * (Brazilian Portuguese)</a>
 */
public class BitcointradeWithdraw {

  private final String code;
  private final String originAddress;
  private final String destinationAddress;
  private final BigDecimal amount;
  private final BigDecimal minerFee;
  private final BitcointradeFeeType minerFeeType;
  private final Integer taxIndex;
  private final Integer taxIndexCalculated;
  private final BigDecimal taxAmount;
  private final BitcointradeWithdrawStatus status;
  private final String createDate;
  private final String updateDate;
  private final Object transactionId;
  private final Object link;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  public BitcointradeWithdraw(@JsonProperty("code") String code, @JsonProperty("origin_address") String originAddress,
      @JsonProperty("destination_address") String destinationAddress, @JsonProperty("amount") BigDecimal amount,
      @JsonProperty("miner_fee") BigDecimal minerFee, @JsonProperty("miner_fee_type") String minerFeeType,
      @JsonProperty("tax_index") Integer taxIndex, @JsonProperty("tax_index_calculated") Integer taxIndexCalculated,
      @JsonProperty("tax_amount") BigDecimal taxAmount, @JsonProperty("status") String status, @JsonProperty("create_date") String createDate,
      @JsonProperty("update_date") String updateDate, @JsonProperty("transaction_id") Object transactionId, @JsonProperty("link") Object link) {

    this.code = code;
    this.originAddress = originAddress;
    this.destinationAddress = destinationAddress;
    this.amount = amount;
    this.minerFee = minerFee;
    this.minerFeeType = BitcointradeFeeType.from(minerFeeType);
    this.taxIndex = taxIndex;
    this.taxIndexCalculated = taxIndexCalculated;
    this.taxAmount = taxAmount;
    this.status = BitcointradeWithdrawStatus.from(status);
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.transactionId = transactionId;
    this.link = link;
  }

  public String getCode() {

    return code;
  }

  public String getOriginAddress() {

    return originAddress;
  }

  public String getDestinationAddress() {

    return destinationAddress;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public BigDecimal getMinerFee() {

    return minerFee;
  }

  public BitcointradeFeeType getMinerFeeType() {

    return minerFeeType;
  }

  public Integer getTaxIndex() {

    return taxIndex;
  }

  public Integer getTaxIndexCalculated() {

    return taxIndexCalculated;
  }

  public BigDecimal getTaxAmount() {

    return taxAmount;
  }

  public BitcointradeWithdrawStatus getStatus() {

    return status;
  }

  public String getCreateDate() {

    return createDate;
  }

  public String getUpdateDate() {

    return updateDate;
  }

  public Object getTransactionId() {

    return transactionId;
  }

  public Object getLink() {

    return link;
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
    return new ToStringBuilder(this).append("code", code).append("originAddress", originAddress)
        .append("destinationAddress", destinationAddress).append("amount", amount).append("minerFee", minerFee)
        .append("minerFeeType", minerFeeType).append("taxIndex", taxIndex)
        .append("taxIndexCalculated", taxIndexCalculated).append("taxAmount", taxAmount).append("status", status)
        .append("createDate", createDate).append("updateDate", updateDate).append("transactionId", transactionId)
        .append("link", link).append("additionalProperties", additionalProperties).toString();
  }
}
