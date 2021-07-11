package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests {@link BitcoinTradeFeeType} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeFeeTypeTest {

  @Test
  public void testFromNameFast() throws Exception {

    BitcoinTradeFeeType expected = BitcoinTradeFeeType.FAST;
    BitcoinTradeFeeType actual = BitcoinTradeFeeType.from("fast");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameRegular() throws Exception {

    BitcoinTradeFeeType expected = BitcoinTradeFeeType.REGULAR;
    BitcoinTradeFeeType actual = BitcoinTradeFeeType.from("regular");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameSlow() throws Exception {

    BitcoinTradeFeeType expected = BitcoinTradeFeeType.SLOW;
    BitcoinTradeFeeType actual = BitcoinTradeFeeType.from("slow");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullMustReturnNull() throws Exception {

    assertNull(BitcoinTradeFeeType.from(null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcoinTradeFeeType.from(""));
  }
}
