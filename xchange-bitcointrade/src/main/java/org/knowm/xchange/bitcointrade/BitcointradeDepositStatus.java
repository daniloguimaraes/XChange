package org.knowm.xchange.bitcointrade;

/**
 * @author Danilo Guimaraes
 */
public enum BitcointradeDepositStatus {

  CONFIRMATION_PENDING("confirmation_pending"),
  CONFIRMED("confirmed"),
  CANCELED("canceled");

  private final String depositStatus;

  BitcointradeDepositStatus(String depositStatus) {
    this.depositStatus = depositStatus;
  }

  public String getDepositStatus() {
    return depositStatus;
  }

  /**
   * Create an instance of {@link BitcointradeDepositStatus} based on the String.
   *
   * @param depositStatus the deposit status
   * @return an instance of {@link BitcointradeDepositStatus} or {@code null}
   */
  public static BitcointradeDepositStatus from(String depositStatus) {

    for (BitcointradeDepositStatus depositStatusType : values()) {
      if (depositStatusType.getDepositStatus().equalsIgnoreCase(depositStatus)) {
        return depositStatusType;
      }
    }
    return null;
  }
}
