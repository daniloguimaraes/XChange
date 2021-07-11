package org.knowm.xchange.bitcointrade;

/**
 * BitcoinTrade Exchange deposit status
 *
 * @author Danilo Guimaraes
 */
public enum BitcoinTradeDepositStatus {
  CONFIRMATION_PENDING("confirmation_pending"),
  CONFIRMED("confirmed"),
  CANCELED("canceled");

  private final String depositStatus;

  BitcoinTradeDepositStatus(String depositStatus) {

    this.depositStatus = depositStatus;
  }

  public String getDepositStatus() {

    return depositStatus;
  }

  /**
   * Create an instance of {@link BitcoinTradeDepositStatus} based on the String.
   *
   * @param depositStatus the deposit status
   * @return an instance of {@link BitcoinTradeDepositStatus} or {@code null}
   */
  public static BitcoinTradeDepositStatus from(String depositStatus) {

    for (BitcoinTradeDepositStatus depositStatusType : values()) {
      if (depositStatusType.getDepositStatus().equalsIgnoreCase(depositStatus)) {
        return depositStatusType;
      }
    }
    return null;
  }
}
