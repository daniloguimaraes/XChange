package org.knowm.xchange.bitcointrade.dto;

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
 * Pagination object representation. Example:
 *
 * <pre>
 * "pagination": {
      "total_pages": 1,
      "current_page": 1,
      "page_size": 10,
      "registers_count": 2
   }
 * </pre>
 *
 * @author Danilo Guimaraes
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total_pages", "current_page", "page_size", "registers_count" })
public class Pagination {

  private final Integer totalPages;
  private final Integer currentPage;
  private final Integer pageSize;
  private final Integer registersCount;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * Constructor
   *
   * @param totalPages number of total pages
   * @param currentPage the current page number
   * @param pageSize number of records per page
   * @param registersCount the total amount of records on all pages ({@code pageSize} X {@code totalPages}, approximate)
   */
  public Pagination(@JsonProperty("total_pages") Integer totalPages, @JsonProperty("current_page") Integer currentPage,
      @JsonProperty("page_size") Integer pageSize, @JsonProperty("registers_count") Integer registersCount) {

    super();
    this.totalPages = totalPages;
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.registersCount = registersCount;
  }

  public Integer getTotalPages() {

    return totalPages;
  }

  public Integer getCurrentPage() {

    return currentPage;
  }

  public Integer getPageSize() {

    return pageSize;
  }

  public Integer getRegistersCount() {

    return registersCount;
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

    return new ToStringBuilder(this).append("totalPages", totalPages).append("currentPage", currentPage).append("pageSize", pageSize)
        .append("registersCount", registersCount).append("additionalProperties", additionalProperties).toString();
  }
}