package org.knowm.xchange.bitcointrade.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Base class for all responses at BitcoinTrade Exchange API.
 *
 * <p>All success responses follow the pattern:
 *
 * <pre>
 *   {
 *    message: null,
 *    data: {}
 *   }
 * </pre>
 *
 * <p>And all error responses follow:
 *
 * <pre>
 *   {
 *    message: "A typical error description",
 *    data: null
 *   }
 * </pre>
 *
 * @param <T> the class representing the 'data' field
 * @author Danilo Guimaraes
 * @see <a href="https://apidocs.bitcointrade.com.br/#intro">BitcoinTrade API Docs</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "data"})
public abstract class BitcoinTradeBaseResponse<T> {

  protected final Object message;
  protected final T data;
  @JsonIgnore protected Map<String, Object> additionalProperties = new HashMap<>();

  @JsonCreator
  public BitcoinTradeBaseResponse(
      @JsonProperty("message") Object message, @JsonProperty("data") T data) {

    this.message = message;
    this.data = data;
  }

  public Object getMessage() {
    return message;
  }

  public T getData() {
    return data;
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
        .append("message", message)
        .append("data", data)
        .append("additionalProperties", additionalProperties)
        .toString();
  }
}
