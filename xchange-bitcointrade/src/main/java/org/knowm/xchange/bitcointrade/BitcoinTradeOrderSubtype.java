package org.knowm.xchange.bitcointrade;

/**
 * BitcoinTrade Exchange order sub-type
 *
 * @author Danilo Guimaraes
 */
public enum BitcoinTradeOrderSubtype {
  LIMITED("limited"),
  MARKET("market");

  private final String subtype;

  BitcoinTradeOrderSubtype(String subtype) {

    this.subtype = subtype;
  }

  public String getSubtype() {

    return subtype;
  }

  /**
   * Create an instance of {@link BitcoinTradeOrderSubtype} based on a String.
   *
   * @param subtype the order sub type
   * @return an instance of {@link BitcoinTradeOrderSubtype} or {@code null}
   */
  public static BitcoinTradeOrderSubtype from(String subtype) {

    for (BitcoinTradeOrderSubtype subtypeEnum : values()) {
      if (subtypeEnum.getSubtype().equals(subtype)) {
        return subtypeEnum;
      }
    }
    return null;
  }
}
