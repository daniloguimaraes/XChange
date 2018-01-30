package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Danilo Guimaraes
 */
public class BitcointradeDepositStatusTest {

  @Test
  public void testFromNamePending() throws Exception {

    BitcointradeDepositStatus expected = BitcointradeDepositStatus.CONFIRMATION_PENDING;
    BitcointradeDepositStatus actual = BitcointradeDepositStatus.from("confirmation_pending");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameConfirmed() throws Exception {

    BitcointradeDepositStatus expected = BitcointradeDepositStatus.CONFIRMED;
    BitcointradeDepositStatus actual = BitcointradeDepositStatus.from("confirmed");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameCanceled() throws Exception {

    BitcointradeDepositStatus expected = BitcointradeDepositStatus.CANCELED;
    BitcointradeDepositStatus actual = BitcointradeDepositStatus.from("canceled");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullMustReturnNull() throws Exception {

    assertNull(BitcointradeDepositStatus.from(null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcointradeDepositStatus.from(""));
  }

}