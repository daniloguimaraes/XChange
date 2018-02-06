package org.knowm.xchange.bitcointrade;

/**
 * Bitcointrade Exchange order sub-type
 *
 * @author Danilo Guimaraes
 */
public enum BitcointradeOrderSubtype {

  LIMITED("limited"),
  MARKET("market");

  private final String subtype;

  BitcointradeOrderSubtype(String subtype) {

    this.subtype = subtype;
  }

  public String getSubtype() {

    return subtype;
  }

  /**
   * Create an instance of {@link BitcointradeOrderSubtype} based on a String.
   *
   * @param subtype the order sub type
   * @return an instance of {@link BitcointradeOrderSubtype} or {@code null}
   */
  public static BitcointradeOrderSubtype from(String subtype) {

    for (BitcointradeOrderSubtype subtypeEnum : values()) {
      if (subtypeEnum.getSubtype().equals(subtype)) {
        return subtypeEnum;
      }
    }
    return null;
  }
}
