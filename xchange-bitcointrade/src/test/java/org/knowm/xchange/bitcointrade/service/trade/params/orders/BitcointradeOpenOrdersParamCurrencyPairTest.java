package org.knowm.xchange.bitcointrade.service.trade.params.orders;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * Tests {@link BitcointradeOpenOrdersParamCurrencyPair} class.
 *
 * @author Danilo Guimar&atilde;es
 */
public class BitcointradeOpenOrdersParamCurrencyPairTest {

  private static final String START_TIME = "2018-01-05";
  private static final String END_TIME = "2018-02-05";
  private static final String TYPE = "buy";
  private static final int PAGE_SIZE = 200;
  private static final int CURRENT_PAGE = 1;

  private BitcointradeOpenOrdersParamCurrencyPair sut;

  @Before
  public void setUp() throws Exception {

     sut = new BitcointradeOpenOrdersParamCurrencyPair.Builder(CurrencyPair.BTC_BRL)
        .startTime(START_TIME)
        .endTime(END_TIME)
        .type(TYPE)
        .pageSize(PAGE_SIZE)
        .currentPage(CURRENT_PAGE)
        .build();
  }

  @Test
  public void testBuilder() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(sut.getCurrencyPair()).isEqualTo(CurrencyPair.BTC_BRL);
    softly.assertThat(sut.getStartTime()).isEqualTo(START_TIME);
    softly.assertThat(sut.getEndTime()).isEqualTo(END_TIME);
    softly.assertThat(sut.getType()).isEqualTo(TYPE);
    softly.assertThat(sut.getPageSize()).isEqualTo(PAGE_SIZE);
    softly.assertThat(sut.getCurrentPage()).isEqualTo(CURRENT_PAGE);

    softly.assertAll();
  }
}