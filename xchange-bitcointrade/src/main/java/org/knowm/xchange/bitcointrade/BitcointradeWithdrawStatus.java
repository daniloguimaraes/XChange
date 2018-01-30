package org.knowm.xchange.bitcointrade;

/**
 * @author Danilo Guimaraes
 */
public enum BitcointradeWithdrawStatus {

  PENDING("pending"),
  CONFIRMED("confirmed"),
  CANCELED("canceled");

  private final String withdrawStatus;

  BitcointradeWithdrawStatus(String withdrawStatus) {

    this.withdrawStatus = withdrawStatus;
  }

  public String getWithdrawStatus() {

    return withdrawStatus;
  }

  /**
   * Create an instance of {@link BitcointradeWithdrawStatus} based on the String.
   *
   * @param withdrawStatus the withdraw status
   * @return an instance of {@link BitcointradeWithdrawStatus} or {@code null}
   */
  public static BitcointradeWithdrawStatus from(String withdrawStatus) {

    for (BitcointradeWithdrawStatus withdrawStatusType : values()) {
      if (withdrawStatusType.getWithdrawStatus().equals(withdrawStatus)) {
        return withdrawStatusType;
      }
    }
    return null;
  }
}
