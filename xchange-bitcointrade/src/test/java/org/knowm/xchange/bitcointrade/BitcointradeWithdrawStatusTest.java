package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests {@link BitcointradeWithdrawStatus} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeWithdrawStatusTest {

  @Test
  public void testFromNamePending() throws Exception {

    BitcointradeWithdrawStatus expected = BitcointradeWithdrawStatus.PENDING;
    BitcointradeWithdrawStatus actual = BitcointradeWithdrawStatus.from("pending");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameConfirmed() throws Exception {

    BitcointradeWithdrawStatus expected = BitcointradeWithdrawStatus.CONFIRMED;
    BitcointradeWithdrawStatus actual = BitcointradeWithdrawStatus.from("confirmed");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcointradeWithdrawStatus expected = BitcointradeWithdrawStatus.CANCELED;
    BitcointradeWithdrawStatus actual = BitcointradeWithdrawStatus.from("canceled");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullMustReturnNull() throws Exception {

    assertNull(BitcointradeWithdrawStatus.from(null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcointradeWithdrawStatus.from(""));
  }

}