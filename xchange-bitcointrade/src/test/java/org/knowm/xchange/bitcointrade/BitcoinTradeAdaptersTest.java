package org.knowm.xchange.bitcointrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWalletBalanceResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeOrderBookResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTickerResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTickerResponseTest;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;

/**
 * Tests the {@link BitcoinTradeAdapters} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeAdaptersTest {

  private static BitcoinTradeOrderBook bitcointradeOrderBook;
  private static BitcoinTradePublicTradeResponse bitcointradePublicTradeResponses;
  private static BitcoinTradeWalletBalanceResponse bitcoinTradeWalletBalanceResponse;

  private static OrderBook orderBook;
  private static Ticker ticker;

  @BeforeClass
  public static void setUp() throws Exception {

    BitcoinTradeOrderBookResponse bitcointradeOrderBookResponse =
        loadBitcoinTradeOrderBookFromExampleData();
    bitcointradeOrderBook = bitcointradeOrderBookResponse.getData();

    bitcoinTradeWalletBalanceResponse = loadBitcoinTradeWalletBalanceFromExampleData();

    BitcoinTradeTickerResponseTest.setUp();
    ticker =
        BitcoinTradeAdapters.adaptBitcoinTradeTicker(
            BitcoinTradeTickerResponseTest.sut, CurrencyPair.BTC_BRL);
  }

  @Test
  public void testOrderBookAdapter() throws Exception {

    orderBook = BitcoinTradeAdapters.adaptBitcoinTradeOrderBook(bitcointradeOrderBook, null);

    final SoftAssertions softly = new SoftAssertions();

    List<LimitOrder> bidsLimitOrders = orderBook.getBids();
    softly.assertThat(bidsLimitOrders.size()).isEqualTo(950);
    softly
        .assertThat(bidsLimitOrders.get(300).getOriginalAmount())
        .isEqualTo(new BigDecimal("0.00175412"));

    List<LimitOrder> asksLimitOrders = orderBook.getAsks();
    softly.assertThat(asksLimitOrders.size()).isEqualTo(2585);
    softly.assertThat(asksLimitOrders.get(300).getLimitPrice()).isEqualTo(new BigDecimal("39000"));

    softly.assertThat(orderBook.getTimeStamp()).isNull();

    softly.assertAll();
  }

  @Test
  public void testPublicOrdersAdapter() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    List<LimitOrder> bidsOrders =
        BitcoinTradeAdapters.adaptBitcoinTradePublicOrders(
            bitcointradeOrderBook.getBids(), Order.OrderType.BID, null);
    softly.assertThat(bidsOrders.size()).isEqualTo(950);
    softly
        .assertThat(bidsOrders.get(300).getOriginalAmount())
        .isEqualTo(new BigDecimal("0.00175412"));

    List<LimitOrder> asksOrders =
        BitcoinTradeAdapters.adaptBitcoinTradePublicOrders(
            bitcointradeOrderBook.getAsks(), Order.OrderType.ASK, null);
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

  @Test
  public void testBitcoinTradeWalletBalance() {
    final AccountInfo accountInfo = BitcoinTradeAdapters
        .adaptAccountInfo(bitcoinTradeWalletBalanceResponse);

    SoftAssertions.assertSoftly((softly) -> {
      softly.assertThat(accountInfo.getWallets()).hasSize(1);
      softly.assertThat(accountInfo.getWallets().get(null).getBalances()).hasSize(8);
    });
  }

  private static BitcoinTradeOrderBookResponse loadBitcoinTradeOrderBookFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/org/knowm/xchange/bitcointrade/dto/marketdata/example-orderbook-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeOrderBookResponse.class);
  }

  private static BitcoinTradeTickerResponse loadBitcoinTradeTickerFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/org/knowm/xchange/bitcointrade/dto/marketdata/example-ticker-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeTickerResponse.class);
  }

  private static BitcoinTradePublicTradeResponse[] loadBitcointoyouPublicTradesFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/org/knowm/xchange/bitcointrade/dto/marketdata/example-public-trades-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradePublicTradeResponse[].class);
  }

  private static BitcoinTradeWalletBalanceResponse loadBitcoinTradeWalletBalanceFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/org/knowm/xchange/bitcointrade/dto/account/example-wallets-balance-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeWalletBalanceResponse.class);
  }
}
