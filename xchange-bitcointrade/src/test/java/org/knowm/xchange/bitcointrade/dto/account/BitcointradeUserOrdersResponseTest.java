package org.knowm.xchange.bitcointrade.dto.account;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.bitcointrade.BitcointradeAdaptersTest;
import org.knowm.xchange.bitcointrade.BitcointradeOrderStatus;
import org.knowm.xchange.bitcointrade.BitcointradeOrderSubtype;
import org.knowm.xchange.bitcointrade.BitcointradeOrderType;
import org.knowm.xchange.bitcointrade.dto.PaginatedTest;
import org.knowm.xchange.currency.Currency;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests {@link BitcointradeUserOrdersResponse} class.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeUserOrdersResponseTest extends PaginatedTest {

  public static BitcointradeUserOrdersResponse sut;

  @BeforeClass
  public static void setUp() throws Exception {

    sut = loadBitcointradeUserOrdersFromExampleData();
  }

  private static BitcointradeUserOrdersResponse loadBitcointradeUserOrdersFromExampleData() throws IOException {

    InputStream is = BitcointradeAdaptersTest.class.getResourceAsStream("/account/example-user-orders-data.json");

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(is, BitcointradeUserOrdersResponse.class);
  }

  @Test
  public void testPagination() throws Exception {

    testPagination(sut.getData().getPagination(), 1, 1, 100, 21);
  }

  @Test
  public void testWithdrawList() throws Exception {

    List<BitcointradeUserOrder> orders = sut.getData().getOrders();

    final SoftAssertions softly = new SoftAssertions();

    softly.assertThat(orders).isNotNull();
    softly.assertThat(orders.size()).isEqualTo(5);

    BitcointradeUserOrder withdrawal = orders.get(0);
    softly.assertThat(withdrawal).isNotNull();
    softly.assertThat(withdrawal.getId()).isEqualTo("U2FsdGVkX19BlCM4tNmxuvt24vgigdOyrEPUvGXIiEU=");
    softly.assertThat(withdrawal.getCode()).isEqualTo("SkvtQoOZf");
    softly.assertThat(withdrawal.getType()).isEqualTo(BitcointradeOrderType.BUY);
    softly.assertThat(withdrawal.getSubtype()).isEqualTo(BitcointradeOrderSubtype.LIMITED);
    softly.assertThat(withdrawal.getRequestedAmount()).isEqualTo(new BigDecimal("0.02347418"));
    softly.assertThat(withdrawal.getRemainingAmount()).isEqualTo(new BigDecimal("0"));
    softly.assertThat(withdrawal.getUnitPrice()).isEqualTo(new BigDecimal("42600"));
    softly.assertThat(withdrawal.getStatus()).isEqualTo(BitcointradeOrderStatus.EXECUTED_COMPLETELY);
    softly.assertThat(withdrawal.getCreateDate()).isEqualTo("2017-12-08T23:42:54.960Z");
    softly.assertThat(withdrawal.getUpdateDate()).isEqualTo("2017-12-13T21:48:48.817Z");
    softly.assertThat(withdrawal.getCurrencyCode()).isEqualTo(Currency.BTC.toString());
    softly.assertThat(withdrawal.getTotalPrice()).isEqualTo(new BigDecimal("1000"));
    softly.assertThat(withdrawal.getExecutedAmount()).isEqualTo(new BigDecimal("0.02347418"));
    softly.assertThat(withdrawal.getRemainingPrice()).isEqualTo(new BigDecimal("0"));

    softly.assertAll();
  }

}