package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.BitcointradeOrderType;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#9fe41816-3d20-e53e-9273-643c95279dc4">Bitcointrade API - Trades Documentation
 * (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "amount", "unit_price", "active_order_code", "passive_order_code", "date" })
public class BitcointradePublicTrade {

  private final BitcointradeOrderType type;
  private final String typeAsString;
  private final BigDecimal amount;
  private final BigDecimal unitPrice;
  private final String activeOrderCode;
  private final String passiveOrderCode;
  private final String date;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * Constructor
   *
   * @param type
   * @param amount
   * @param unitPrice
   * @param activeOrderCode
   * @param passiveOrderCode
   * @param date
   */
  public BitcointradePublicTrade(@JsonProperty("type") String type, @JsonProperty("amount") BigDecimal amount,
      @JsonProperty("unit_price") BigDecimal unitPrice, @JsonProperty("active_order_code") String activeOrderCode,
      @JsonProperty("passive_order_code") String passiveOrderCode, @JsonProperty("date") String date) {

    super();
    this.type = BitcointradeOrderType.from(type);
    this.typeAsString = type;
    this.amount = amount;
    this.unitPrice = unitPrice;
    this.activeOrderCode = activeOrderCode;
    this.passiveOrderCode = passiveOrderCode;
    this.date = date;
  }

  public BitcointradeOrderType getType() {

    return type;
  }

  public String getTypeAsString() {
    return typeAsString;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public BigDecimal getUnitPrice() {

    return unitPrice;
  }

  public String getActiveOrderCode() {

    return activeOrderCode;
  }

  public String getPassiveOrderCode() {

    return passiveOrderCode;
  }

  public String getDate() {

    return date;
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

    return new ToStringBuilder(this).append("type", type).append("amount", amount).append("unitPrice", unitPrice)
        .append("activeOrderCode", activeOrderCode).append("passiveOrderCode", passiveOrderCode).append("date", date)
        .append("additionalProperties", additionalProperties).toString();
  }

}
