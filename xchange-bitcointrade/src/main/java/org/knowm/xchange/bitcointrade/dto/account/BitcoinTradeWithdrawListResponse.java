package org.knowm.xchange.bitcointrade.dto.account;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.knowm.xchange.bitcointrade.dto.BitcoinTradeBaseResponse;
import org.knowm.xchange.bitcointrade.dto.Pagination;

/**
 * @author Danilo Guimaraes
 * @see <a
 *     href="https://apidocs.bitcointrade.com.br/#2f4b6643-ae82-d9a3-8cb9-d025e92982fe">BitcoinTrade
 *     API - Withdraw List Documentation (Brazilian Portuguese)</a>
 */
public class BitcoinTradeWithdrawListResponse
    extends BitcoinTradeBaseResponse<BitcoinTradeWithdrawListResponse.Data> {

  @JsonCreator
  public BitcoinTradeWithdrawListResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") Data data) {

    super(message, data);
  }

  /** */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonPropertyOrder({"pagination", "withdrawals"})
  public static class Data {

    private final Pagination pagination;
    private final List<BitcoinTradeWithdraw> withdrawals;
    @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * @param withdrawals
     * @param pagination
     */
    public Data(
        @JsonProperty("pagination") Pagination pagination,
        @JsonProperty("withdrawals") List<BitcoinTradeWithdraw> withdrawals) {

      super();
      this.pagination = pagination;
      this.withdrawals = withdrawals;
    }

    public Pagination getPagination() {

      return pagination;
    }

    public List<BitcoinTradeWithdraw> getWithdrawals() {

      return withdrawals;
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
          .append("pagination", pagination)
          .append("withdrawals", withdrawals)
          .append("additionalProperties", additionalProperties)
          .toString();
    }
  }
}
