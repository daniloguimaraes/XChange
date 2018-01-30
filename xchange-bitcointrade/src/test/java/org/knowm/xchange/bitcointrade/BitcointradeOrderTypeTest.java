package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knowm.xchange.dto.Order;

/**
 * @author Danilo Guimaraes
 * @since 29/01/2018
 */
public class BitcointradeOrderTypeTest {

  @Test
  public void testFromNameWaiting() throws Exception {

    BitcointradeOrderType expected = BitcointradeOrderType.BUY;
    BitcointradeOrderType actual = BitcointradeOrderType.from("buy");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcointradeOrderType expected = BitcointradeOrderType.SELL;
    BitcointradeOrderType actual = BitcointradeOrderType.from("sell");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderTypeBidMustReturnOrderTypeBuy() throws Exception {

    Order.OrderType expected = Order.OrderType.BID;
    Order.OrderType actual = BitcointradeOrderType.from(BitcointradeOrderType.BUY);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderTypeAskMustReturnOrderTypeSell() throws Exception {

    Order.OrderType expected = Order.OrderType.ASK;
    Order.OrderType actual = BitcointradeOrderType.from(BitcointradeOrderType.SELL);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullStringMustReturnNull() throws Exception {
    assertNull(BitcointradeOrderType.from((String) null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcointradeOrderType.from(""));
  }

  @Test
  public void testFromUsingNullOrderTypeMustReturnNull() throws Exception {
    assertNull(BitcointradeOrderType.from((BitcointradeOrderType) null));
  }

}