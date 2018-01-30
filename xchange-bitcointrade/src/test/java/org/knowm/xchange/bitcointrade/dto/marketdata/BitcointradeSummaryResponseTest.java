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
 * Tests {@link BitcointradeSummaryResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeSummaryResponseTest {

  public static BitcointradeSummaryResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeSummaryFromExampleData();
  }

  private static BitcointradeSummaryResponse loadBitcointradeSummaryFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-summary-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeSummaryResponse.class);
  }

  @Test
  public void testSummary() throws Exception {

    final BitcointradeSummary summary = sut.getData();

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(summary).isNotNull();
    softly.assertThat(summary.getUnitPrice24h()).isEqualTo(new BigDecimal("54049"));
    softly.assertThat(summary.getVolume24h()).isEqualTo(new BigDecimal("0"));
    softly.assertThat(summary.getLastTransactionUnitPrice()).isEqualTo(new BigDecimal("54049"));
    softly.assertThat(summary.getCurrency()).isEqualTo("BTC");

    softly.assertAll();
  }
}