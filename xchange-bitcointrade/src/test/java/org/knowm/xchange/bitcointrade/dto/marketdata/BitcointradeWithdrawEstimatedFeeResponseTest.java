package org.knowm.xchange.bitcointrade.dto.marketdata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcointradeAdaptersTest;
import org.knowm.xchange.bitcointrade.BitcointradeFeeType;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Danilo Guimaraes
 * @since 29/01/2018
 */
public class BitcointradeWithdrawEstimatedFeeResponseTest {

  public static BitcointradeWithdrawEstimatedFeeResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeTickerFromExampleData();
  }

  private static BitcointradeWithdrawEstimatedFeeResponse loadBitcointradeTickerFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/marketdata/example-withdraw-fee-estimate-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeWithdrawEstimatedFeeResponse.class);
  }

  @Test
  public void testEstimatedFee() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    List<BitcointradeWithdrawEstimatedFee> estimatedFeeList = sut.getData();
    softly.assertThat(estimatedFeeList).isNotNull();

    BitcointradeWithdrawEstimatedFee fastFee = estimatedFeeList.get(0);
    softly.assertThat(fastFee.getName()).isEqualTo(BitcointradeFeeType.FAST);
    softly.assertThat(fastFee.getAmount()).isEqualTo(new BigDecimal("0.0010074"));

    BitcointradeWithdrawEstimatedFee regularFee = estimatedFeeList.get(1);
    softly.assertThat(regularFee.getName()).isEqualTo(BitcointradeFeeType.REGULAR);
    softly.assertThat(regularFee.getAmount()).isEqualTo(new BigDecimal("0.00075774"));

    BitcointradeWithdrawEstimatedFee slowFee = estimatedFeeList.get(2);
    softly.assertThat(slowFee.getName()).isEqualTo(BitcointradeFeeType.SLOW);
    softly.assertThat(slowFee.getAmount()).isEqualTo(new BigDecimal("0.0005037"));

    softly.assertAll();
  }

}