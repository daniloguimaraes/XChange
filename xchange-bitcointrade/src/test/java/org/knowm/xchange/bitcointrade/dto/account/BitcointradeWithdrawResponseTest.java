package org.knowm.xchange.bitcointrade.dto.account;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcointradeAdaptersTest;
import org.knowm.xchange.bitcointrade.BitcointradeFeeType;
import org.knowm.xchange.bitcointrade.BitcointradeWithdrawStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests {@lunk BitcointradeWithdrawResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeWithdrawResponseTest {

  public static BitcointradeWithdrawResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeWithdrawFromExampleData();
  }

  private static BitcointradeWithdrawResponse loadBitcointradeWithdrawFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/account/example-withdraw-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeWithdrawResponse.class);
  }

  @Test
  public void testWithdraw() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    BitcointradeWithdraw withdrawal = sut.getData();
    softly.assertThat(withdrawal).isNotNull();
    softly.assertThat(withdrawal.getCode()).isEqualTo("HJzpwYDrz");
    softly.assertThat(withdrawal.getOriginAddress()).isEqualTo("mpe8FKtvDktBN12XJoFp5FRpdktySxD8wg");
    softly.assertThat(withdrawal.getDestinationAddress()).isEqualTo("1AU4BoYaxSunkEWi4gMYXJ41c9bvQG6Wa2");
    softly.assertThat(withdrawal.getAmount()).isEqualTo(new BigDecimal("0.0094963"));
    softly.assertThat(withdrawal.getMinerFee()).isEqualTo(new BigDecimal("0.0005037"));
    softly.assertThat(withdrawal.getMinerFeeType()).isEqualTo(BitcointradeFeeType.SLOW);
    softly.assertThat(withdrawal.getTaxIndex()).isEqualTo(0);
    softly.assertThat(withdrawal.getTaxIndexCalculated()).isEqualTo(0);
    softly.assertThat(withdrawal.getTaxAmount()).isEqualTo("0");
    softly.assertThat(withdrawal.getStatus()).isEqualTo(BitcointradeWithdrawStatus.PENDING);
    softly.assertThat(withdrawal.getCreateDate()).isEqualTo("2018-01-25T16:37:15.443Z");
    softly.assertThat(withdrawal.getUpdateDate()).isEqualTo("2018-01-25T16:37:15.017Z");
    softly.assertThat(withdrawal.getTransactionId()).isNull();
    softly.assertThat(withdrawal.getLink()).isNull();

    softly.assertAll();
  }

}