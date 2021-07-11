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
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderStatus;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderSubtype;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderType;
import org.knowm.xchange.bitcointrade.dto.PaginatedTest;
import org.knowm.xchange.currency.Currency;

/**
 * Tests {@link BitcoinTradeUserOrdersResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeUserOrdersResponseTest extends PaginatedTest {

  public static BitcoinTradeUserOrdersResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeUserOrdersFromExampleData();
  }

  private static BitcoinTradeUserOrdersResponse loadBitcointradeUserOrdersFromExampleData()
      throws IOException {

    InputStream is =
        BitcoinTradeAdaptersTest.class.getResourceAsStream(
            "/account/example-user-orders-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcoinTradeUserOrdersResponse.class);
  }

  @Test
  public void testPagination() throws Exception {

    testPagination(sut.getData().getPagination(), 1, 1, 100, 21);
  }

  @Test
  public void testWithdrawList() throws Exception {

    List<BitcoinTradeUserOrder> orders = sut.getData().getOrders();

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(orders).isNotNull();
    softly.assertThat(orders.size()).isEqualTo(5);

    BitcoinTradeUserOrder withdrawal = orders.get(0);
    softly.assertThat(withdrawal).isNotNull();
    softly.assertThat(withdrawal.getId()).isEqualTo("U2FsdGVkX19BlCM4tNmxuvt24vgigdOyrEPUvGXIiEU=");
    softly.assertThat(withdrawal.getCode()).isEqualTo("SkvtQoOZf");
    softly.assertThat(withdrawal.getType()).isEqualTo(BitcoinTradeOrderType.BUY);
    softly.assertThat(withdrawal.getSubtype()).isEqualTo(BitcoinTradeOrderSubtype.LIMITED);
    softly.assertThat(withdrawal.getRequestedAmount()).isEqualTo(new BigDecimal("0.02347418"));
    softly.assertThat(withdrawal.getRemainingAmount()).isEqualTo(new BigDecimal("0"));
    softly.assertThat(withdrawal.getUnitPrice()).isEqualTo(new BigDecimal("42600"));
    softly
        .assertThat(withdrawal.getStatus())
        .isEqualTo(BitcoinTradeOrderStatus.EXECUTED_COMPLETELY);
    softly.assertThat(withdrawal.getCreateDate()).isEqualTo("2017-12-08T23:42:54.960Z");
    softly.assertThat(withdrawal.getUpdateDate()).isEqualTo("2017-12-13T21:48:48.817Z");
    softly.assertThat(withdrawal.getCurrencyCode()).isEqualTo(Currency.BTC.toString());
    softly.assertThat(withdrawal.getTotalPrice()).isEqualTo(new BigDecimal("1000"));
    softly.assertThat(withdrawal.getExecutedAmount()).isEqualTo(new BigDecimal("0.02347418"));
    softly.assertThat(withdrawal.getRemainingPrice()).isEqualTo(new BigDecimal("0"));

    softly.assertAll();
  }
}
