package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcoinTradeAdaptersTest;

/**
 * Tests {@link BitcoinTradeEstimatedPriceResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeEstimatedPriceResponseTest {

  public static BitcoinTradeEstimatedPriceResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcoinTradeEstimatedPriceFromExampleData();
  }

  private static BitcoinTradeEstimatedPriceResponse loadBitcoinTradeEstimatedPriceFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/marketdata/example-estimated-price-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeEstimatedPriceResponse.class);
  }

  @Test
  public void testEstimatedPrice() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    final BigDecimal trades = sut.getData().getPrice();
    softly.assertThat(trades).isNotNull();
    softly.assertThat(trades).isEqualTo(new BigDecimal("54049"));

    softly.assertAll();
  }
}
