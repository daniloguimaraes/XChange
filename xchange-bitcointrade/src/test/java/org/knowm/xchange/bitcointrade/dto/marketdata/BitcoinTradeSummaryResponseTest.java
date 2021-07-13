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
 * Tests {@link BitcoinTradeSummaryResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeSummaryResponseTest {

  public static BitcoinTradeSummaryResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcoinTradeSummaryFromExampleData();
  }

  private static BitcoinTradeSummaryResponse loadBitcoinTradeSummaryFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/org/knowm/xchange/bitcointrade/dto/marketdata/example-summary-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeSummaryResponse.class);
  }

  @Test
  public void testSummary() throws Exception {

    final BitcoinTradeSummary summary = sut.getData();

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(summary).isNotNull();
    softly.assertThat(summary.getUnitPrice24h()).isEqualTo(new BigDecimal("54049"));
    softly.assertThat(summary.getVolume24h()).isEqualTo(new BigDecimal("0"));
    softly.assertThat(summary.getLastTransactionUnitPrice()).isEqualTo(new BigDecimal("54049"));
    softly.assertThat(summary.getPair()).isEqualTo("BRLBTC");
    softly.assertThat(summary.getMaxPrice()).isEqualTo(new BigDecimal("54049"));
    softly.assertThat(summary.getMinPrice()).isEqualTo(new BigDecimal("54049"));

    softly.assertAll();
  }
}
