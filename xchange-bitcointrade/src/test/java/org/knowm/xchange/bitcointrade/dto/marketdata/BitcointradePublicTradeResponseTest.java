package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcointradeAdaptersTest;
import org.knowm.xchange.bitcointrade.BitcointradeOrderType;
import org.knowm.xchange.bitcointrade.dto.PaginatedTest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests {@link BitcointradeTickerResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradePublicTradeResponseTest extends PaginatedTest {

  public static BitcointradePublicTradeResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradePublicTradeFromExampleData();
  }

  private static BitcointradePublicTradeResponse loadBitcointradePublicTradeFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-public-trades-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradePublicTradeResponse.class);
  }

  @Test
  public void testPagination() throws Exception {

    testPagination(sut.getData().getPagination(), 8829, 1, 20, 176564);
  }

  @Test
  public void testTrades() throws Exception {

    final List<BitcointradePublicTrade> trades = sut.getData().getTrades();

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(trades).isNotNull();
    softly.assertThat(trades.size()).isEqualTo(20);

    BitcointradePublicTrade trade = trades.get(0);
    softly.assertThat(trade).isNotNull();
    softly.assertThat(trade.getType()).isEqualTo(BitcointradeOrderType.BUY);
    softly.assertThat(trade.getAmount()).isEqualTo(new BigDecimal("0.19525906"));
    softly.assertThat(trade.getUnitPrice()).isEqualTo(new BigDecimal("36919.93"));
    softly.assertThat(trade.getActiveOrderCode()).isEqualTo("Sy9XFpjBM");
    softly.assertThat(trade.getPassiveOrderCode()).isEqualTo("B1mQqKjSM");
    softly.assertThat(trade.getDate()).isEqualTo("2018-01-28T22:05:21.740Z");

    softly.assertAll();
  }
}