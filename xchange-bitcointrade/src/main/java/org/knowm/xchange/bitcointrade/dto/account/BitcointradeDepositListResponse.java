package org.knowm.xchange.bitcointrade.dto.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @see <a href="https://apidocs.bitcointrade.com.br/#34810ae9-69e0-8c83-5c61-01d81162be10">Bitcointrade API - Deposit List Documentation
 * (Brazilian Portuguese)</a>
 */
public class BitcointradeDepositListResponse extends BitcointradeBaseResponse<BitcointradeDepositListResponse.Data> {

  @JsonCreator
  public BitcointradeDepositListResponse(@JsonProperty("message") Object message, @JsonProperty("data") Data data) {

    super(message, data);
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonPropertyOrder({ "pagination", "deposits" })
  public static class Data {

    private final Pagination pagination;
    private final List<BitcointradeDeposit> deposits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * @param pagination
     * @param deposits
     */
    public Data(@JsonProperty("pagination") Pagination pagination, @JsonProperty("withdrawals") List<BitcointradeDeposit> deposits) {

      super();
      this.pagination = pagination;
      this.deposits = deposits;
    }

    public Pagination getPagination() {

      return pagination;
    }

    public List<BitcointradeDeposit> getDeposits() {

      return deposits;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {

      return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {

      this.additionalProperties.put(name, value);
    }
  }
}
