package org.knowm.xchange.bitcointrade.dto.account;

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
import org.knowm.xchange.bitcointrade.BitcoinTradeWithdrawStatus;
import org.knowm.xchange.bitcointrade.dto.PaginatedTest;

/**
 * Tests {@link BitcoinTradeWithdrawListResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeWithdrawListResponseTest extends PaginatedTest {

  public static BitcoinTradeWithdrawListResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcoinTradeWithdrawListFromExampleData();
  }

  private static BitcoinTradeWithdrawListResponse loadBitcoinTradeWithdrawListFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/account/example-withdraw-list-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeWithdrawListResponse.class);
  }

  @Test
  public void testPagination() throws Exception {

    testPagination(sut.getData().getPagination(), 1, 1, 10, 2);
  }

  @Test
  public void testWithdrawList() throws Exception {

    final List<BitcoinTradeWithdraw> trades = sut.getData().getWithdrawals();

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(trades).isNotNull();
    softly.assertThat(trades.size()).isEqualTo(2);

    BitcoinTradeWithdraw withdrawal = trades.get(0);
    softly.assertThat(withdrawal).isNotNull();
    softly.assertThat(withdrawal.getCode()).isEqualTo("HJzpwYDrz");
    softly
        .assertThat(withdrawal.getOriginAddress())
        .isEqualTo("mpe8FKtvDktBN1FGToFpYN2pdktySxD8wg");
    softly
        .assertThat(withdrawal.getDestinationAddress())
        .isEqualTo("1AU4BoYaxSunkFTTEMYXJ41c9bvQG6Wa2");
    softly.assertThat(withdrawal.getAmount()).isEqualTo(new BigDecimal("0.0094963"));
    softly.assertThat(withdrawal.getMinerFee()).isEqualTo(new BigDecimal("0.0005037"));
    softly.assertThat(withdrawal.getMinerFeeType()).isEqualTo(BitcoinTradeFeeType.SLOW);
    softly.assertThat(withdrawal.getTaxIndex()).isEqualTo(0);
    softly.assertThat(withdrawal.getTaxIndexCalculated()).isEqualTo(0);
    softly.assertThat(withdrawal.getTaxAmount()).isEqualTo("0");
    softly.assertThat(withdrawal.getStatus()).isEqualTo(BitcoinTradeWithdrawStatus.PENDING);
    softly.assertThat(withdrawal.getCreateDate()).isEqualTo("2018-01-25T16:37:15.443Z");
    softly.assertThat(withdrawal.getUpdateDate()).isEqualTo("2018-01-25T16:37:15.017Z");
    softly.assertThat(withdrawal.getTransactionId()).isNull();
    softly.assertThat(withdrawal.getLink()).isNull();

    softly.assertAll();
  }
}
