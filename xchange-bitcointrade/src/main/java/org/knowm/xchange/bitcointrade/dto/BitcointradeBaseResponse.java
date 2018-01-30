package org.knowm.xchange.bitcointrade.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Base class for all responses at Bitcointrade Exchange API.
 *
 * <p>
 * All success responses follow the pattern:
 * </p>
 *
 * <pre>
 *   {
 *    message: null,
 *    data: {}
 *   }
 * </pre>
 *
 * <p>
 * And all error responses follow:
 * </p>
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
 * @see <a href="https://apidocs.bitcointrade.com.br/#intro">Bitcointrade API Docs</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "data" })
public abstract class BitcointradeBaseResponse<T> {

  protected final Object message;
  protected final T data;
  @JsonIgnore
  protected Map<String, Object> additionalProperties = new HashMap<>();

  @JsonCreator
  public BitcointradeBaseResponse(@JsonProperty("message") Object message, @JsonProperty("data") T data) {

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
}
