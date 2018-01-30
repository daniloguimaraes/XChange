package org.knowm.xchange.bitcointrade;

/**
 * Bitcointrade Exchange Fee Type.
 *
 * @author Danilo Guimaraes
 */
public enum BitcointradeFeeType {

  FAST("fast"),
  REGULAR("regular"),
  SLOW("slow");

  private final String name;

  BitcointradeFeeType(String name) {
    this.name = name;
  }

  public String getName() {

    return name;
  }

  public static BitcointradeFeeType from(String name) {

    for (BitcointradeFeeType bitcointradeFeeType : values()) {
      if (bitcointradeFeeType.getName().equals(name)) {
        return bitcointradeFeeType;
      }
    }
    return null;
  }
}
