package org.knowm.xchange.bitcointrade.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTicker;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTickerResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

/**
 * Integration tests for {@link BitcoinTradeMarketDataServiceRaw}
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeMarketDataServiceRawIntegration {

  private static BitcoinTradeMarketDataServiceRaw sut;

  @BeforeClass
  public static void setUp() throws Exception {

    Exchange bitcointradeExchange =
        ExchangeFactory.INSTANCE.createExchange(BitcoinTradeExchange.class.getName());
    bitcointradeExchange.remoteInit();
    sut = new BitcoinTradeMarketDataService((BitcoinTradeExchange) bitcointradeExchange);
  }

  @Test
  public void testValidTicker() throws Exception {

    BitcoinTradeTickerResponse bitcointradeTickerResponse =
        sut.getBitcoinTradeTicker(new CurrencyPair("BTC", "BRL"));

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(bitcointradeTickerResponse.getMessage()).isNull();

    BitcoinTradeTicker ticker = bitcointradeTickerResponse.getData();
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

  @Rule public ExpectedException exception = ExpectedException.none();

  @Test
  public void testGetTickerWithAnInvalidCurrency() {

    exception.expect(ExchangeException.class);
    exception.expectMessage("Código do par de moedas inválido");

    // BitcoinTrade doesn't trade Tether (USDT), yet.
    sut.getBitcoinTradeTicker(new CurrencyPair("USDT", "BRL"));
  }
}
