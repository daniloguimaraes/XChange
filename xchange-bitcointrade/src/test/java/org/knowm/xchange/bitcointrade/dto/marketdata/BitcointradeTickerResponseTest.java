package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcointradeAdaptersTest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests {@link BitcointradeTickerResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeTickerResponseTest {

  public static BitcointradeTickerResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeTickerFromExampleData();
  }

  private static BitcointradeTickerResponse loadBitcointradeTickerFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-ticker-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeTickerResponse.class);
  }

  @Test
  public void testTicker() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    BitcointradeTicker ticker = sut.getData();
    softly.assertThat(ticker).isNotNull();

    softly.assertThat(ticker.getHigh()).isEqualTo(new BigDecimal("36749.99"));
    softly.assertThat(ticker.getLow()).isEqualTo(new BigDecimal("36000"));
    softly.assertThat(ticker.getVolume()).isEqualTo(new BigDecimal("18.88589195"));
    softly.assertThat(ticker.getTradesQuantity()).isEqualTo(385);
    softly.assertThat(ticker.getLast()).isEqualTo(new BigDecimal("36500.03"));
    softly.assertThat(ticker.getBuy()).isEqualTo(new BigDecimal("36500.02"));
    softly.assertThat(ticker.getSell()).isEqualTo(new BigDecimal("36745"));
    softly.assertThat(ticker.getDate()).isEqualTo("2018-01-28T04:39:46.469Z");

    softly.assertAll();
  }
}