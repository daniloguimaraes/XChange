package org.knowm.xchange.bitcointrade.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcoinTradeAdaptersTest;
import org.knowm.xchange.bitcointrade.BitcoinTradeFeeType;

/**
 * Tests {@link BitcoinTradeWithdrawEstimatedFeeResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeWithdrawEstimatedFeeResponseTest {

  public static BitcoinTradeWithdrawEstimatedFeeResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeTickerFromExampleData();
  }

  private static BitcoinTradeWithdrawEstimatedFeeResponse loadBitcointradeTickerFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/marketdata/example-withdraw-fee-estimate-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeWithdrawEstimatedFeeResponse.class);
  }

  @Test
  public void testEstimatedFee() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    List<BitcoinTradeWithdrawEstimatedFee> estimatedFeeList = sut.getData();
    softly.assertThat(estimatedFeeList).isNotNull();

    BitcoinTradeWithdrawEstimatedFee fastFee = estimatedFeeList.get(0);
    softly.assertThat(fastFee.getName()).isEqualTo(BitcoinTradeFeeType.FAST);
    softly.assertThat(fastFee.getAmount()).isEqualTo(new BigDecimal("0.0010074"));

    BitcoinTradeWithdrawEstimatedFee regularFee = estimatedFeeList.get(1);
    softly.assertThat(regularFee.getName()).isEqualTo(BitcoinTradeFeeType.REGULAR);
    softly.assertThat(regularFee.getAmount()).isEqualTo(new BigDecimal("0.00075774"));

    BitcoinTradeWithdrawEstimatedFee slowFee = estimatedFeeList.get(2);
    softly.assertThat(slowFee.getName()).isEqualTo(BitcoinTradeFeeType.SLOW);
    softly.assertThat(slowFee.getAmount()).isEqualTo(new BigDecimal("0.0005037"));

    softly.assertAll();
  }
}
