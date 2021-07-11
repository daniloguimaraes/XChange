package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knowm.xchange.dto.Order;

/**
 * Tests {@link BitcoinTradeOrderType} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeOrderTypeTest {

  @Test
  public void testFromNameWaiting() throws Exception {

    BitcoinTradeOrderType expected = BitcoinTradeOrderType.BUY;
    BitcoinTradeOrderType actual = BitcoinTradeOrderType.from("buy");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcoinTradeOrderType expected = BitcoinTradeOrderType.SELL;
    BitcoinTradeOrderType actual = BitcoinTradeOrderType.from("sell");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderTypeBidMustReturnOrderTypeBuy() throws Exception {

    Order.OrderType expected = Order.OrderType.BID;
    Order.OrderType actual = BitcoinTradeOrderType.from(BitcoinTradeOrderType.BUY);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderTypeAskMustReturnOrderTypeSell() throws Exception {

    Order.OrderType expected = Order.OrderType.ASK;
    Order.OrderType actual = BitcoinTradeOrderType.from(BitcoinTradeOrderType.SELL);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullStringMustReturnNull() throws Exception {
    assertNull(BitcoinTradeOrderType.from((String) null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcoinTradeOrderType.from(""));
  }

  @Test
  public void testFromUsingNullOrderTypeMustReturnNull() throws Exception {
    assertNull(BitcoinTradeOrderType.from((BitcoinTradeOrderType) null));
  }
}
