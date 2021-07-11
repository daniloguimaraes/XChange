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
import org.knowm.xchange.bitcointrade.BitcoinTradeDepositStatus;
import org.knowm.xchange.bitcointrade.dto.PaginatedTest;

/**
 * Tests {@link BitcoinTradeDepositListResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeDepositListResponseTest extends PaginatedTest {

  public static BitcoinTradeDepositListResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeDepositListFromExampleData();
  }

  private static BitcoinTradeDepositListResponse loadBitcointradeDepositListFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/account/example-deposit-list-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeDepositListResponse.class);
  }

  @Test
  public void testPagination() throws Exception {

    testPagination(sut.getData().getPagination(), 1, 1, 10, 2);
  }

  @Test
  public void testDepositList() throws Exception {

    final SoftAssertions softly = new SoftAssertions();

    final List<BitcoinTradeDeposit> deposits = sut.getData().getDeposits();

    softly.assertThat(deposits).isNotNull();
    softly.assertThat(deposits.size()).isEqualTo(2);

    BitcoinTradeDeposit deposit = deposits.get(0);
    softly.assertThat(deposit).isNotNull();
    softly.assertThat(deposit.getCode()).isEqualTo("SJbLkdTh_");
    softly
        .assertThat(deposit.getHash())
        .isEqualTo("4f87d009d88524e627b60c6fbe4ebe671a99lk9ad72aab17e0963c5fbef118e2");
    softly.assertThat(deposit.getAmount()).isEqualTo(new BigDecimal("2.29999999"));
    softly.assertThat(deposit.getStatus()).isEqualTo(BitcoinTradeDepositStatus.CONFIRMED);
    softly.assertThat(deposit.getCreateDate()).isEqualTo("2017-10-12T22:40:08.273Z");
    softly.assertThat(deposit.getConfirmationDate()).isEqualTo("2017-10-12T22:40:08.273Z");

    softly.assertAll();
  }
}
