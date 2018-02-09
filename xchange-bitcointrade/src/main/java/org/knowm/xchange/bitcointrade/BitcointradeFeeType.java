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

  public String getFeeType() {

    return name;
  }

  /**
   * Create an instance of (@link BitcointradeFeeType} based on a String.
   *
   * @param feeType the fee type
   * @return an instance of {@link BitcointradeFeeType} or {@code null}
   */
  public static BitcointradeFeeType from(String feeType) {

    for (BitcointradeFeeType bitcointradeFeeTypeEnum : values()) {
      if (bitcointradeFeeTypeEnum.getFeeType().equals(feeType)) {
        return bitcointradeFeeTypeEnum;
      }
    }
    return null;
  }
}
