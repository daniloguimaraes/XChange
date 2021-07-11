package org.knowm.xchange.bitcointrade;

/**
 * Bitcointrade Exchange Fee Type.
 *
 * @author Danilo Guimaraes
 */
public enum BitcoinTradeFeeType {
  FAST("fast"),
  REGULAR("regular"),
  SLOW("slow");

  private final String name;

  BitcoinTradeFeeType(String name) {

    this.name = name;
  }

  public String getFeeType() {

    return name;
  }

  /**
   * Create an instance of (@link BitcointradeFeeType} based on a String.
   *
   * @param feeType the fee type
   * @return an instance of {@link BitcoinTradeFeeType} or {@code null}
   */
  public static BitcoinTradeFeeType from(String feeType) {

    for (BitcoinTradeFeeType bitcoinTradeFeeTypeEnum : values()) {
      if (bitcoinTradeFeeTypeEnum.getFeeType().equals(feeType)) {
        return bitcoinTradeFeeTypeEnum;
      }
    }
    return null;
  }
}
