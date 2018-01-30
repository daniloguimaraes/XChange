package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Bitcointrade Exchange Order book representation.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#dc3695f5-6129-e35c-153d-c629aee8fd48">Bitcointrade API - Trades Documentation
 * (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"asks", "bids"})
public class BitcointradeOrderBook {

  private final List<BidsAndAsks> bids;
  private final List<BidsAndAsks> asks;

  /**
   * @param bids
   * @param asks
   */
  @JsonCreator
  public BitcointradeOrderBook(@JsonProperty("bids") List<BidsAndAsks> bids, @JsonProperty("asks") List<BidsAndAsks> asks) {

    super();
    this.bids = bids;
    this.asks = asks;
  }

  public List<BidsAndAsks> getBids() {

    return bids;
  }

  public List<BidsAndAsks> getAsks() {

    return asks;
  }

  @Override
  public String toString() {

    return new ToStringBuilder(this).append("bids", bids).append("asks", asks).toString();
  }

  /**
   *
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonPropertyOrder({"unit_price", "code", "amount"})
  public static class BidsAndAsks {

    private final BigDecimal unitPrice;
    private final String code;
    private final BigDecimal amount;

    /**
     * @param unitPrice
     * @param code
     * @param amount
     */
    @JsonCreator
    public BidsAndAsks(@JsonProperty("unit_price") BigDecimal unitPrice, @JsonProperty("code") String code,
        @JsonProperty("amount") BigDecimal amount) {

      super();
      this.unitPrice = unitPrice;
      this.code = code;
      this.amount = amount;
    }

    public BigDecimal getUnitPrice() {

      return unitPrice;
    }

    public String getCode() {

      return code;
    }

    public BigDecimal getAmount() {

      return amount;
    }

    @Override
    public String toString() {

      return new ToStringBuilder(this).append("unitPrice", unitPrice).append("code", code).append("amount", amount).toString();
    }

  }

}
