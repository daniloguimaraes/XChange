package org.knowm.xchange.bitcointrade.dto;

import org.assertj.core.api.SoftAssertions;

/**
 * Helper class for testing objects that have {@link Pagination} object..
 *
 * @author Danilo Guimaraes
 */
public class PaginatedTest {

  protected void testPagination(Pagination pagination, int totalPages, int currentPage, int pageSize, int registersCount) {
    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(pagination).isNotNull();
    softly.assertThat(pagination.getTotalPages()).isEqualTo(totalPages);
    softly.assertThat(pagination.getCurrentPage()).isEqualTo(currentPage);
    softly.assertThat(pagination.getPageSize()).isEqualTo(pageSize);
    softly.assertThat(pagination.getRegistersCount()).isEqualTo(registersCount);

    softly.assertAll();
  }
}
