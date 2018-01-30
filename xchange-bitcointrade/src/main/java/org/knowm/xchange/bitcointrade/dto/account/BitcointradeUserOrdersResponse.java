package org.knowm.xchange.bitcointrade.dto.account;

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
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#989dcc17-e4fa-1262-fa35-589d47dd6b43">Bitcointrade API - User Orders Documentation
 * (Brazilian Portuguese)</a>
 */
public class BitcointradeUserOrdersResponse extends BitcointradeBaseResponse<BitcointradeUserOrdersResponse.Data> {

  @JsonCreator
  public BitcointradeUserOrdersResponse(@JsonProperty("message") Object message, @JsonProperty("data") Data data) {

    super(message, data);
  }

  /**
   *
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonPropertyOrder({ "pagination", "orders" })
  public static class Data {

    private final Pagination pagination;
    private final List<BitcointradeUserOrder> orders;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();


    /**
     *
     * @param orders
     * @param pagination
     */
    public Data(@JsonProperty("pagination") Pagination pagination, @JsonProperty("orders") List<BitcointradeUserOrder> orders) {

      super();
      this.pagination = pagination;
      this.orders = orders;
    }

    public Pagination getPagination() {

      return pagination;
    }

    public List<BitcointradeUserOrder> getOrders() {

      return orders;
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
      return new ToStringBuilder(this).append("pagination", pagination).append("orders", orders)
          .append("additionalProperties", additionalProperties).toString();
    }
  }
}
