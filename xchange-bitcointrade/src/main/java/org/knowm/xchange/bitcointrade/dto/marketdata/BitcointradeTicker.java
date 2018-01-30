package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Bitcointrade Ticker itself.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#8e6f6b73-b2f8-c03a-9d60-a0159f2c6ce0">Bitcointrade API - Ticker Documentation
 * (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "high", "low", "volume", "trades_quantity", "last", "buy", "sell", "date" })
public class BitcointradeTicker {

  private final BigDecimal high;
  private final BigDecimal low;
  private final BigDecimal volume;
  private final Integer tradesQuantity;
  private final BigDecimal last;
  private final BigDecimal buy;
  private final BigDecimal sell;
  private final String date;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * @param high
   * @param low
   * @param volume
   * @param tradesQuantity
   * @param last
   * @param buy
   * @param sell
   * @param date
   */
  public BitcointradeTicker(@JsonProperty("high") BigDecimal high, @JsonProperty("low") BigDecimal low, @JsonProperty("volume") BigDecimal volume,
      @JsonProperty("trades_quantity") Integer tradesQuantity, @JsonProperty("last") BigDecimal last, @JsonProperty("buy") BigDecimal buy,
      @JsonProperty("sell") BigDecimal sell, @JsonProperty("date") String date) {

    super();
    this.high = high;
    this.low = low;
    this.volume = volume;
    this.tradesQuantity = tradesQuantity;
    this.last = last;
    this.buy = buy;
    this.sell = sell;
    this.date = date;
  }

//  /**
//   *
//   * @param map
//   */
//  @JsonCreator
//  public BitcointradeTicker(Map<String, Map<String, String>> map) {
//    super();
//    Map<String, String> ticker = map.get("ticker");
//    this.high = new BigDecimal(ticker.get("high"));
//    this.low = new BigDecimal(ticker.get("low"));
//    this.volume = new BigDecimal(ticker.get("volume"));
//    this.tradesQuantity = new Integer(ticker.get("trades_quantity"));
//    this.last = new BigDecimal(ticker.get("last"));
//    this.buy = new BigDecimal(ticker.get("buy"));
//    this.sell = new BigDecimal(ticker.get("sell"));
//    this.date = ticker.get("date");
//  }

  public BigDecimal getHigh() {

    return high;
  }

  public BigDecimal getLow() {

    return low;
  }

  public BigDecimal getVolume() {

    return volume;
  }

  public Integer getTradesQuantity() {

    return tradesQuantity;
  }

  public BigDecimal getLast() {

    return last;
  }

  public BigDecimal getBuy() {

    return buy;
  }

  public BigDecimal getSell() {

    return sell;
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

    return new ToStringBuilder(this).append("high", high).append("low", low).append("volume", volume).append("tradesQuantity", tradesQuantity)
        .append("last", last).append("buy", buy).append("sell", sell).append("date", date).append("additionalProperties", additionalProperties)
        .toString();
  }

}