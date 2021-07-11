package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;

/** @author Danilo Guimaraes */
public class BitcoinTradeDepositStatusTest {

  @Test
  public void testFromNamePending() throws Exception {

    BitcoinTradeDepositStatus expected = BitcoinTradeDepositStatus.CONFIRMATION_PENDING;
    BitcoinTradeDepositStatus actual = BitcoinTradeDepositStatus.from("confirmation_pending");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameConfirmed() throws Exception {

    BitcoinTradeDepositStatus expected = BitcoinTradeDepositStatus.CONFIRMED;
    BitcoinTradeDepositStatus actual = BitcoinTradeDepositStatus.from("confirmed");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcoinTradeDepositStatus expected = BitcoinTradeDepositStatus.CANCELED;
    BitcoinTradeDepositStatus actual = BitcoinTradeDepositStatus.from("canceled");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullMustReturnNull() throws Exception {

    assertNull(BitcoinTradeDepositStatus.from(null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcoinTradeDepositStatus.from(""));
  }
}
