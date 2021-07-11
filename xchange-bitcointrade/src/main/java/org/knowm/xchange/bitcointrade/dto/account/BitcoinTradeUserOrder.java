package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderStatus;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderSubtype;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderType;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#989dcc17-e4fa-1262-fa35-589d47dd6b43">Bitcointrade
 *     API - User Orders Documentation (Brazilian Portuguese)</a>
 */
public class BitcoinTradeUserOrder {

  private final String id;
  private final String code;
  private final BitcoinTradeOrderType type;
  private final BitcoinTradeOrderSubtype subtype;
  private final BigDecimal requestedAmount;
  private final BigDecimal remainingAmount;
  private final BigDecimal unitPrice;
  private final BitcoinTradeOrderStatus status;
  private final String createDate;
  private final String updateDate;
  private final String currencyCode;
  private final BigDecimal totalPrice;
  private final BigDecimal executedAmount;
  private final BigDecimal remainingPrice;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * Constructor
   *
   * @param id the order ID
   * @param code the order code
   * @param type the order type (buy/sell)
   * @param subtype the order subtype (limited/market)
   * @param requestedAmount the requested amount
   * @param remainingAmount the remaining amount
   * @param unitPrice the order unit price
   * @param status the order status
   * @param createDate the order creation date
   * @param updateDate the order update date
   * @param currencyCode the currency code (e.g. BTC)
   * @param totalPrice the total price (requestedAmount times unitPrice)
   * @param executedAmount executed amonut
   * @param remainingPrice remaining price
   */
  public BitcoinTradeUserOrder(
      @JsonProperty("id") String id,
      @JsonProperty("code") String code,
      @JsonProperty("type") String type,
      @JsonProperty("subtype") String subtype,
      @JsonProperty("requested_amount") BigDecimal requestedAmount,
      @JsonProperty("remaining_amount") BigDecimal remainingAmount,
      @JsonProperty("unit_price") BigDecimal unitPrice,
      @JsonProperty("status") String status,
      @JsonProperty("create_date") String createDate,
      @JsonProperty("update_date") String updateDate,
      @JsonProperty("currencyCode") String currencyCode,
      @JsonProperty("total_price") BigDecimal totalPrice,
      @JsonProperty("executed_amount") BigDecimal executedAmount,
      @JsonProperty("remaining_price") BigDecimal remainingPrice) {

    super();
    this.id = id;
    this.code = code;
    this.type = BitcoinTradeOrderType.from(type);
    this.subtype = BitcoinTradeOrderSubtype.from(subtype);
    this.requestedAmount = requestedAmount;
    this.remainingAmount = remainingAmount;
    this.unitPrice = unitPrice;
    this.status = BitcoinTradeOrderStatus.from(status);
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.currencyCode = currencyCode;
    this.totalPrice = totalPrice;
    this.executedAmount = executedAmount;
    this.remainingPrice = remainingPrice;
  }

  public String getId() {

    return id;
  }

  public String getCode() {

    return code;
  }

  public BitcoinTradeOrderType getType() {

    return type;
  }

  public BitcoinTradeOrderSubtype getSubtype() {

    return subtype;
  }

  public BigDecimal getRequestedAmount() {

    return requestedAmount;
  }

  public BigDecimal getRemainingAmount() {

    return remainingAmount;
  }

  public BigDecimal getUnitPrice() {

    return unitPrice;
  }

  public BitcoinTradeOrderStatus getStatus() {

    return status;
  }

  public String getCreateDate() {

    return createDate;
  }

  public String getUpdateDate() {

    return updateDate;
  }

  public String getCurrencyCode() {

    return currencyCode;
  }

  public BigDecimal getTotalPrice() {

    return totalPrice;
  }

  public BigDecimal getExecutedAmount() {

    return executedAmount;
  }

  public BigDecimal getRemainingPrice() {

    return remainingPrice;
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
        .append("id", id)
        .append("code", code)
        .append("type", type)
        .append("subtype", subtype)
        .append("requestedAmount", requestedAmount)
        .append("remainingAmount", remainingAmount)
        .append("unitPrice", unitPrice)
        .append("status", status)
        .append("createDate", createDate)
        .append("updateDate", updateDate)
        .append("currencyCode", currencyCode)
        .append("totalPrice", totalPrice)
        .append("executedAmount", executedAmount)
        .append("remainingPrice", remainingPrice)
        .append("additionalProperties", additionalProperties)
        .toString();
  }
}
