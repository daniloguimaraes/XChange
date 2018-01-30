package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knowm.xchange.dto.Order;

/**
 * Tests {@link BitcointradeOrderStatus} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeOrderStatusTest {

  @Test
  public void testFromNameExecutedCompletely() throws Exception {

    BitcointradeOrderStatus expected = BitcointradeOrderStatus.EXECUTED_COMPLETELY;
    BitcointradeOrderStatus actual = BitcointradeOrderStatus.from("executed_completely");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameExecutedPartially() throws Exception {

    BitcointradeOrderStatus expected = BitcointradeOrderStatus.EXECUTED_PARTIALLY;
    BitcointradeOrderStatus actual = BitcointradeOrderStatus.from("executed_partially");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameWaiting() throws Exception {

    BitcointradeOrderStatus expected = BitcointradeOrderStatus.WAITING;
    BitcointradeOrderStatus actual = BitcointradeOrderStatus.from("waiting");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcointradeOrderStatus expected = BitcointradeOrderStatus.CANCELED;
    BitcointradeOrderStatus actual = BitcointradeOrderStatus.from("canceled");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusExecutedCompletelyMustReturnFilled() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.FILLED;
    Order.OrderStatus actual = BitcointradeOrderStatus.from(BitcointradeOrderStatus.EXECUTED_COMPLETELY);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusExecutedPartiallyMustReturnFilled() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.PARTIALLY_FILLED;
    Order.OrderStatus actual = BitcointradeOrderStatus.from(BitcointradeOrderStatus.EXECUTED_PARTIALLY);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromOrderStatusWaitingMustReturnNew() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.NEW;
    Order.OrderStatus actual = BitcointradeOrderStatus.from(BitcointradeOrderStatus.WAITING);

    assertEquals(expected, actual);
  }


  @Test
  public void testFromOrderStatusCanceledMustReturnCanceled() throws Exception {
    Order.OrderStatus expected = Order.OrderStatus.CANCELED;
    Order.OrderStatus actual = BitcointradeOrderStatus.from(BitcointradeOrderStatus.CANCELED);

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullStringMustReturnNull() throws Exception {

    assertNull(BitcointradeOrderStatus.from((String) null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcointradeOrderStatus.from(""));
  }

  @Test
  public void testFromUsingNullOrderStatusMustReturnNull() throws Exception {

    assertNull(BitcointradeOrderStatus.from((BitcointradeOrderStatus) null));
  }

}