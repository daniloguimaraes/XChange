package org.knowm.xchange.bitcointrade;

import org.knowm.xchange.currency.CurrencyPair;

/**
 * {@link CurrencyPair}'s default {@code toString} implementation returns 'base / counter'.
 *
 * <p>
 * Unfortunately Bitcointrade's API works with a 'counterbase' format.
 * </p>
 *
 * For example: 'BTC/BRL' becomes 'BRLBTC'.
 *
 * @see <a href="https://apidocs.bitcointrade.com.br/#pares-disponiveis">BitcoinTrade API - Pares disponiveis</a>
 */
public final class BitcointradeCurrencyPairNormalizer {

  /**
   * Normalize a instance of {@link CurrencyPair} with Bitcointrade's specific format.
   *
   * @param currencyPair the currency pair
   * @return normalized currency pair or 'BRLBTC' if 'currencyPair' argument is null
   */
  public static String normalize(CurrencyPair currencyPair) {

    if (currencyPair == null) {
      return "BRLBTC";
    }

    return currencyPair.counter.toString().concat(currencyPair.base.toString());
  }

}
