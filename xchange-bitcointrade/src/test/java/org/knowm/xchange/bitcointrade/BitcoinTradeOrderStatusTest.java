package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knowm.xchange.dto.Order;

/**
 * Tests {@link BitcoinTradeOrderStatus} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeOrderStatusTest {

  @Test
  public void testFromNameExecutedCompletely() throws Exception {

    BitcoinTradeOrderStatus expected = BitcoinTradeOrderStatus.EXECUTED_COMPLETELY;
    BitcoinTradeOrderStatus actual = BitcoinTradeOrderStatus.from("executed_completely");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameExecutedPartially() throws Exception {

    BitcoinTradeOrderStatus expected = BitcoinTradeOrderStatus.EXECUTED_PARTIALLY;
    BitcoinTradeOrderStatus actual = BitcoinTradeOrderStatus.from("executed_partially");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameWaiting() throws Exception {

    BitcoinTradeOrderStatus expected = BitcoinTradeOrderStatus.WAITING;
    BitcoinTradeOrderStatus actual = BitcoinTradeOrderStatus.from("waiting");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcoinTradeOrderStatus expected = BitcoinTradeOrderStatus.CANCELED;
    BitcoinTradeOrderStatus actual = BitcoinTradeOrderStatus.from("canceled");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusExecutedCompletelyMustReturnFilled() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.FILLED;
    Order.OrderStatus actual =
        BitcoinTradeOrderStatus.from(BitcoinTradeOrderStatus.EXECUTED_COMPLETELY);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusExecutedPartiallyMustReturnFilled() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.PARTIALLY_FILLED;
    Order.OrderStatus actual =
        BitcoinTradeOrderStatus.from(BitcoinTradeOrderStatus.EXECUTED_PARTIALLY);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusWaitingMustReturnNew() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.NEW;
    Order.OrderStatus actual = BitcoinTradeOrderStatus.from(BitcoinTradeOrderStatus.WAITING);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusCanceledMustReturnCanceled() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.CANCELED;
    Order.OrderStatus actual = BitcoinTradeOrderStatus.from(BitcoinTradeOrderStatus.CANCELED);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullStringMustReturnNull() throws Exception {

    assertNull(BitcoinTradeOrderStatus.from((String) null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcoinTradeOrderStatus.from(""));
  }

  @Test
  public void testFromUsingNullOrderStatusMustReturnNull() throws Exception {

    assertNull(BitcoinTradeOrderStatus.from((BitcoinTradeOrderStatus) null));
  }
}
