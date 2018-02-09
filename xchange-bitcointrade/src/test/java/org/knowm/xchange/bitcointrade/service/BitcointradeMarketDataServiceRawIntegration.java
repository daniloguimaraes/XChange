package org.knowm.xchange.bitcointrade.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitcointrade.BitcointradeExchange;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTicker;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

/**
 * Integration tests for {@link BitcointradeMarketDataServiceRaw}
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeMarketDataServiceRawIntegration {

  private static BitcointradeMarketDataServiceRaw sut;

  @BeforeClass
  public static void setUp() throws Exception {

    Exchange bitcointradeExchange = ExchangeFactory.INSTANCE.createExchange(BitcointradeExchange.class.getName());
    bitcointradeExchange.remoteInit();
    sut = new BitcointradeMarketDataService(bitcointradeExchange);
  }

  @Test
  public void testValidTicker() throws Exception {

    BitcointradeTickerResponse bitcointradeTickerResponse = sut.getBitcointradeTicker(new CurrencyPair("BTC", "BRL"));

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(bitcointradeTickerResponse.getMessage()).isNull();

    BitcointradeTicker ticker = bitcointradeTickerResponse.getData();
    softly.assertThat(ticker).isNotNull();
    softly.assertThat(ticker).isNotNull();
    softly.assertThat(ticker.getHigh()).isNotNull();
    softly.assertThat(ticker.getLow()).isNotNull();
    softly.assertThat(ticker.getVolume()).isNotNull();
    softly.assertThat(ticker.getTradesQuantity()).isNotNull();
    softly.assertThat(ticker.getLast()).isNotNull();
    softly.assertThat(ticker.getBuy()).isNotNull();
    softly.assertThat(ticker.getSell()).isNotNull();
    softly.assertThat(ticker.getDate()).isNotNull();

    softly.assertAll();
  }

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void testGetTickerWithAnInvalidCurrency() {

    exception.expect(ExchangeException.class);
    exception.expectMessage("Moeda inválida");

    // Bitcointrade doesn't trade Ripple (XRP), yet.
    sut.getBitcointradeTicker(new CurrencyPair("XRP", "ABC"));
  }


}