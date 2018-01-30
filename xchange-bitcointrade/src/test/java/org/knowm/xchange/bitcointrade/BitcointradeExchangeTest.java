package org.knowm.xchange.bitcointrade;

import java.util.Map;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.RateLimit;
import org.knowm.xchange.utils.nonce.AtomicLongIncrementalTime2013NonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

/**
 * Testes the {@link BitcointradeExchange} class
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeExchangeTest {

  private Exchange sut;

  @Before
  public void setUp() throws Exception {
    sut = ExchangeFactory.INSTANCE.createExchange(BitcointradeExchange.class.getName());
  }

  @Test
  public void testNonceFactory() throws Exception {

    final SoftAssertions softly = new SoftAssertions();
    SynchronizedValueFactory<Long> nonceFactory = sut.getNonceFactory();

    softly.assertThat(nonceFactory).isNotNull();
    softly.assertThat(nonceFactory).isInstanceOf(AtomicLongIncrementalTime2013NonceFactory.class);

    softly.assertAll();
  }

  @Test
  public void testExchangeMetaData() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    ExchangeMetaData exchangeMetaData = sut.getExchangeMetaData();

    softly.assertThat(exchangeMetaData).isNotNull();
    softly.assertThat(exchangeMetaData.isShareRateLimits()).isFalse();

    softly.assertThat(exchangeMetaData.getCurrencies()).size().isEqualTo(2);

    Map<CurrencyPair, CurrencyPairMetaData> currencyPairs = exchangeMetaData.getCurrencyPairs();
    softly.assertThat(currencyPairs).size().isEqualTo(1);
    softly.assertThat(currencyPairs).size().isEqualTo(1);

    softly.assertAll();
  }
}