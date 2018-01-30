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
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * Integration tests for Bitcointrade Order Book
 *
 * @author Danilo Guimaraes
 */
public class OrderBookFecthIntegration {

  private static OrderBook orderBook;

  @BeforeClass
  public static void setUp() throws Exception {

    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitcointradeExchange.class.getName());
    exchange.remoteInit();
    MarketDataService marketDataService = exchange.getMarketDataService();
    orderBook = marketDataService.getOrderBook(new CurrencyPair(Currency.BTC, Currency.BRL));
  }

  @Test
  public void orderBookTest() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(orderBook).isNotNull();
    softly.assertThat(orderBook.getTimeStamp()).isNull();

    final List<LimitOrder> asks = orderBook.getAsks();
    softly.assertThat(asks).isNotNull();
    softly.assertThat(asks).isNotEmpty();

    final LimitOrder firstAskOrder = asks.get(0);
    softly.assertThat(firstAskOrder).isNotNull();
    softly.assertThat(firstAskOrder.getType()).isNotNull();
    softly.assertThat(firstAskOrder.getType()).isEqualTo(Order.OrderType.ASK);
    softly.assertThat(firstAskOrder.getCurrencyPair()).isNotNull();
    softly.assertThat(firstAskOrder.getId()).isNotNull();
    softly.assertThat(firstAskOrder.getOriginalAmount()).isNotNull();
    softly.assertThat(firstAskOrder.getLimitPrice()).isNotNull();

    final List<LimitOrder> bids = orderBook.getBids();
    softly.assertThat(bids).isNotNull();
    softly.assertThat(bids).isNotEmpty();

    final LimitOrder firstBidOrder = bids.get(0);
    softly.assertThat(firstBidOrder).isNotNull();
    softly.assertThat(firstBidOrder.getType()).isNotNull();
    softly.assertThat(firstBidOrder.getType()).isEqualTo(Order.OrderType.BID);
    softly.assertThat(firstBidOrder.getCurrencyPair()).isNotNull();
    softly.assertThat(firstBidOrder.getId()).isNotNull();
    softly.assertThat(firstBidOrder.getOriginalAmount()).isNotNull();
    softly.assertThat(firstBidOrder.getLimitPrice()).isNotNull();

    softly.assertAll();
  }
}
