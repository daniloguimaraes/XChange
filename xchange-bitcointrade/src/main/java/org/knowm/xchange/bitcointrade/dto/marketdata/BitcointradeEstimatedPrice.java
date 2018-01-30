package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Danilo Guimaraes
 * @since 29/01/2018
 */
public class BitcointradeEstimatedPrice {

  private final BigDecimal price;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();


  /**
   *
   * @param price
   */
  public BitcointradeEstimatedPrice(@JsonProperty("price")BigDecimal price) {

    super();
    this.price = price;
  }

  public BigDecimal getPrice() {

    return price;
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

    return new ToStringBuilder(this).append("price", price).append("additionalProperties", additionalProperties).toString();
  }

}
