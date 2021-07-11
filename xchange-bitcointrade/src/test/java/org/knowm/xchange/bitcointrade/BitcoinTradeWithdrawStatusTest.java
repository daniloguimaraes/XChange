package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link BitcoinTradeWithdrawStatus} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeWithdrawStatusTest {

  @Test
  public void testFromNamePending() throws Exception {

    BitcoinTradeWithdrawStatus expected = BitcoinTradeWithdrawStatus.PENDING;
    BitcoinTradeWithdrawStatus actual = BitcoinTradeWithdrawStatus.from("pending");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameConfirmed() throws Exception {

    BitcoinTradeWithdrawStatus expected = BitcoinTradeWithdrawStatus.CONFIRMED;
    BitcoinTradeWithdrawStatus actual = BitcoinTradeWithdrawStatus.from("confirmed");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcoinTradeWithdrawStatus expected = BitcoinTradeWithdrawStatus.CANCELED;
    BitcoinTradeWithdrawStatus actual = BitcoinTradeWithdrawStatus.from("canceled");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullMustReturnNull() throws Exception {

    assertNull(BitcoinTradeWithdrawStatus.from(null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcoinTradeWithdrawStatus.from(""));
  }
}
