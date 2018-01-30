package org.knowm.xchange.bitcointrade;

/**
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

  public static BitcointradeOrderSubtype from(String subtype) {

    for (BitcointradeOrderSubtype subtypeEnum : values()) {
      if (subtypeEnum.getSubtype().equals(subtype)) {
        return subtypeEnum;
      }
    }
    return null;
  }
}
