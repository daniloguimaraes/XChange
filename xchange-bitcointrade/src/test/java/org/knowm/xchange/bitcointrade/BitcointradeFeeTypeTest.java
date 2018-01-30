package org.knowm.xchange.bitcointrade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests {@link BitcointradeFeeType} enum.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeFeeTypeTest {

  @Test
  public void testFromNameFast() throws Exception {

    BitcointradeFeeType expected = BitcointradeFeeType.FAST;
    BitcointradeFeeType actual = BitcointradeFeeType.from("fast");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameRegular() throws Exception {

    BitcointradeFeeType expected = BitcointradeFeeType.REGULAR;
    BitcointradeFeeType actual = BitcointradeFeeType.from("regular");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromNameSlow() throws Exception {

    BitcointradeFeeType expected = BitcointradeFeeType.SLOW;
    BitcointradeFeeType actual = BitcointradeFeeType.from("slow");

    assertEquals(expected, actual);
  }

  @Test
  public void testFromUsingNullMustReturnNull() throws Exception {

    assertNull(BitcointradeFeeType.from(null));
  }

  @Test
  public void testFromUsingEmptyStringMustReturnNull() throws Exception {

    assertNull(BitcointradeFeeType.from(""));
  }
}