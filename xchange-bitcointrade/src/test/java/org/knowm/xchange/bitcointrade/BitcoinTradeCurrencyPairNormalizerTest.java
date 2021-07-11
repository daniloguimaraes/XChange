package org.knowm.xchange.bitcointrade;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * {@link BitcoinTradeCurrencyPairNormalizer} unit tests.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeCurrencyPairNormalizerTest {

  private static final String BRLBTC = "BRLBTC";

  @Test
  public void testNormalizeNullCurrencyPairShouldReturn_BRLBTC() {
    final String actual = BitcoinTradeCurrencyPairNormalizer.normalize(null);

    assertThat(actual).isEqualTo(BRLBTC);
  }

  @Test
  public void testNormalizeBTCBRLCurrencyPairShouldReturn_BRLBTC() {
    final String actual = BitcoinTradeCurrencyPairNormalizer.normalize(CurrencyPair.BTC_BRL);

    assertThat(actual).isEqualTo(BRLBTC);
  }

  @Test
  public void testNormalizeETHBRLCurrencyPairShouldReturn_USDETH() {
    final String actual = BitcoinTradeCurrencyPairNormalizer.normalize(new CurrencyPair("ETH/BRL"));

    assertThat(actual).isEqualTo("BRLETH");
  }
}
