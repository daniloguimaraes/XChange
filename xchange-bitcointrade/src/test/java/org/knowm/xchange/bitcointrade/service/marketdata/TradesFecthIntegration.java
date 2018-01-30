package org.knowm.xchange.bitcointrade.service.marketdata;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitcointrade.BitcointradeExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * Integration tests for Bitcointrade Trades
 *
 * @author Danilo Guimaraes
 */
public class TradesFecthIntegration {

  private static Trades trades;

  @BeforeClass
  public static void setUp() throws Exception {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitcointradeExchange.class.getName());
    exchange.remoteInit();
    MarketDataService marketDataService = exchange.getMarketDataService();
    trades = marketDataService.getTrades(new CurrencyPair(Currency.BTC, Currency.BRL), null);
  }

  @Test
  public void tradesTest() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(trades).isNotNull();

    final List<Trade> tradeList = TradesFecthIntegration.trades.getTrades();
    softly.assertThat(tradeList).isNotNull();
    softly.assertThat(tradeList).isNotEmpty();

    final Trade firstTrade = tradeList.get(0);
    softly.assertThat(firstTrade).isNotNull();
    softly.assertThat(firstTrade.getType()).isNotNull();
    softly.assertThat(firstTrade.getType()).isNotNull();
    softly.assertThat(firstTrade.getCurrencyPair()).isNotNull();
    softly.assertThat(firstTrade.getId()).isNotNull();
    softly.assertThat(firstTrade.getOriginalAmount()).isNotNull();
    softly.assertThat(firstTrade.getPrice()).isNotNull();
    softly.assertThat(firstTrade.getTimestamp()).isNotNull();

    softly.assertAll();
  }
}
