package org.knowm.xchange.bitcointrade;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBookResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponseTest;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the {@link BitcointradeAdapters} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeAdaptersTest {

  private static BitcointradeOrderBook bitcointradeOrderBook;
  private static BitcointradePublicTradeResponse bitcointradePublicTradeResponses;

  private static OrderBook orderBook;
  private static Ticker ticker;

  @BeforeClass
  public static void setUp() throws Exception {

    BitcointradeOrderBookResponse bitcointradeOrderBookResponse = loadBitcointradeOrderBookFromExampleData();
    bitcointradeOrderBook = bitcointradeOrderBookResponse.getData();

    BitcointradeTickerResponseTest.setUp();
    ticker = BitcointradeAdapters.adaptBitcointradeTicker(BitcointradeTickerResponseTest.sut, CurrencyPair.BTC_BRL);
  }

  @Test
  public void testOrderBookAdapter() throws Exception {

    orderBook = BitcointradeAdapters.adaptBitcointradeOrderBook(bitcointradeOrderBook, null);

    final SoftAssertions softly = new SoftAssertions();

    List<LimitOrder> bidsLimitOrders = orderBook.getBids();
    softly.assertThat(bidsLimitOrders.size()).isEqualTo(950);
    softly.assertThat(bidsLimitOrders.get(300).getOriginalAmount()).isEqualTo(new BigDecimal("0.00175412"));

    List<LimitOrder> asksLimitOrders = orderBook.getAsks();
    softly.assertThat(asksLimitOrders.size()).isEqualTo(2585);
    softly.assertThat(asksLimitOrders.get(300).getLimitPrice()).isEqualTo(new BigDecimal("39000"));

    softly.assertThat(orderBook.getTimeStamp()).isNull();

    softly.assertAll();
  }

  @Test
  public void testPublicOrdersAdapter() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    List<LimitOrder> bidsOrders = BitcointradeAdapters.adaptBitcointradePublicOrders(bitcointradeOrderBook.getBids(), Order.OrderType.BID, null);
    softly.assertThat(bidsOrders.size()).isEqualTo(950);
    softly.assertThat(bidsOrders.get(300).getOriginalAmount()).isEqualTo(new BigDecimal("0.00175412"));

    List<LimitOrder> asksOrders = BitcointradeAdapters.adaptBitcointradePublicOrders(bitcointradeOrderBook.getAsks(), Order.OrderType.ASK, null);
    softly.assertThat(asksOrders.size()).isEqualTo(2585);
    softly.assertThat(asksOrders.get(300).getLimitPrice()).isEqualTo(new BigDecimal("39000"));

    softly.assertAll();
  }

  @Test
  public void testTickerAdapter() throws IOException {

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(ticker.getLast()).isEqualTo(new BigDecimal("36500.03"));
    softly.assertThat(ticker.getBid()).isEqualTo(new BigDecimal("36500.02"));
    softly.assertThat(ticker.getAsk()).isEqualTo(new BigDecimal("36745"));
    softly.assertThat(ticker.getHigh()).isEqualTo(new BigDecimal("36749.99"));
    softly.assertThat(ticker.getLow()).isEqualTo(new BigDecimal("36000"));
    softly.assertThat(ticker.getVolume()).isEqualTo(new BigDecimal("18.88589195"));
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    f.setTimeZone(TimeZone.getTimeZone("UTC"));
    String dateString = f.format(ticker.getTimestamp());
    softly.assertThat(dateString).isEqualTo("2018-01-28 04:39:46");

    softly.assertAll();
  }

  @Test
  public void testPublicTradesAdapter() throws Exception {

//    final SoftAssertions softly = new SoftAssertions();

//    softly.assertThat(bitcointradePublicTradeResponses).isNotNull();

//    softly.assertAll();
  }

  private static BitcointradeOrderBookResponse loadBitcointradeOrderBookFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-orderbook-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeOrderBookResponse.class);
  }

  private static BitcointradeTickerResponse loadBitcointradeTickerFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-ticker-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeTickerResponse.class);
  }

  private static BitcointradePublicTradeResponse[] loadBitcointoyouPublicTradesFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-public-trades-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradePublicTradeResponse[].class);

  }

}