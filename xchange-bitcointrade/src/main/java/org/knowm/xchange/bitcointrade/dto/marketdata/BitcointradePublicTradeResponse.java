package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;
import org.knowm.xchange.bitcointrade.dto.Pagination;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Bitcointrade Exchange Public Trades response representation.
 *
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#9fe41816-3d20-e53e-9273-643c95279dc4">Bitcointrade API - Trades Documentation
 * (Brazilian Portuguese)</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "data" })
public class BitcointradePublicTradeResponse extends BitcointradeBaseResponse<BitcointradePublicTradeResponse.Data> {

  /**
   * Constructor
   *
   * @param message the message, in case of error
   * @param data the data containing trade information
   */
  @JsonCreator
  public BitcointradePublicTradeResponse(@JsonProperty("message") Object message, @JsonProperty("data") Data data) {

    super(message, data);
  }


  @Override
  public String toString() {

    return new ToStringBuilder(this).append("message", message).append("data", data).append("additionalProperties", additionalProperties).toString();
  }

  /**
   *
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonPropertyOrder({ "pagination", "trades" })
  public static class Data {

    private final Pagination pagination;
    private final List<BitcointradePublicTrade> trades;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Constructor
     *
     * @param pagination
     * @param trades
     */
    public Data(@JsonProperty("pagination") Pagination pagination, @JsonProperty("trades") List<BitcointradePublicTrade> trades) {

      super();
      this.pagination = pagination;
      this.trades = trades;
    }

    public Pagination getPagination() {

      return pagination;
    }

    public List<BitcointradePublicTrade> getTrades() {

      return trades;
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

      return new ToStringBuilder(this).append("pagination", pagination).append("trades", trades)
          .append("additionalProperties", additionalProperties).toString();

    }

  }

}
