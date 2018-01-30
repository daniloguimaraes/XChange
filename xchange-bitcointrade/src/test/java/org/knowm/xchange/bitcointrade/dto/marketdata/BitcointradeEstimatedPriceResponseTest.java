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
 * Tests {@link BitcointradeEstimatedPriceResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeEstimatedPriceResponseTest {

  public static BitcointradeEstimatedPriceResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeEstimatedPriceFromExampleData();
  }

  private static BitcointradeEstimatedPriceResponse loadBitcointradeEstimatedPriceFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-estimated-price-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeEstimatedPriceResponse.class);
  }


  @Test
  public void testTrades() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    final BigDecimal trades = sut.getData().getPrice();
    softly.assertThat(trades).isNotNull();
    softly.assertThat(trades).isEqualTo(new BigDecimal("54049"));

    softly.assertAll();
  }

}