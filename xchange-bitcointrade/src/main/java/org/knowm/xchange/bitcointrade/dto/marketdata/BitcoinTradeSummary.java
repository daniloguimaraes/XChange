package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#9a20d5e9-056b-7427-5f22-35f571f60411">Bitcointrade
 *     API - Summary Documentation (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "unit_price_24h",
  "volume_24h",
  "last_transaction_unit_price",
  "pair",
  "max_price",
  "min_price"
})
public class BitcoinTradeSummary {

  private final BigDecimal unitPrice24h;
  private final BigDecimal volume24h;
  private final BigDecimal lastTransactionUnitPrice;
  private final String pair;
  private final BigDecimal maxPrice;
  private final BigDecimal minPrice;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * @param volume24h
   * @param lastTransactionUnitPrice
   * @param pair
   * @param unitPrice24h
   */
  public BitcoinTradeSummary(
      @JsonProperty("unit_price_24h") BigDecimal unitPrice24h,
      @JsonProperty("volume_24h") BigDecimal volume24h,
      @JsonProperty("last_transaction_unit_price") BigDecimal lastTransactionUnitPrice,
      @JsonProperty("pair") String pair,
      @JsonProperty("max_price") BigDecimal maxPrice,
      @JsonProperty("min_price") BigDecimal minPrice) {

    super();
    this.unitPrice24h = unitPrice24h;
    this.volume24h = volume24h;
    this.lastTransactionUnitPrice = lastTransactionUnitPrice;
    this.pair = pair;
    this.maxPrice = maxPrice;
    this.minPrice = minPrice;
  }

  public BigDecimal getUnitPrice24h() {

    return unitPrice24h;
  }

  public BigDecimal getVolume24h() {

    return volume24h;
  }

  public BigDecimal getLastTransactionUnitPrice() {

    return lastTransactionUnitPrice;
  }

  public String getPair() {

    return pair;
  }

  public BigDecimal getMaxPrice() {
    return maxPrice;
  }

  public BigDecimal getMinPrice() {
    return minPrice;
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
        .append("unitPrice24h", unitPrice24h)
        .append("volume24h", volume24h)
        .append("lastTransactionUnitPrice", lastTransactionUnitPrice)
        .append("pair", pair)
        .append("maxPrice", maxPrice)
        .append("minPrice", minPrice)
        .append("additionalProperties", additionalProperties)
        .toString();
  }
}
