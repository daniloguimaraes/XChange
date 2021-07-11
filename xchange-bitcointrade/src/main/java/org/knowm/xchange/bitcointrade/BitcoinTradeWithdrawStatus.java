package org.knowm.xchange.bitcointrade;

/**
 * Bitcointrade Exchange withdraw status
 *
 * @author Danilo Guimaraes
 */
public enum BitcoinTradeWithdrawStatus {
  PENDING("pending"),
  CONFIRMED("confirmed"),
  CANCELED("canceled");

  private final String withdrawStatus;

  BitcoinTradeWithdrawStatus(String withdrawStatus) {

    this.withdrawStatus = withdrawStatus;
  }

  public String getWithdrawStatus() {

    return withdrawStatus;
  }

  /**
   * Create an instance of {@link BitcoinTradeWithdrawStatus} based on the String.
   *
   * @param withdrawStatus the withdraw status
   * @return an instance of {@link BitcoinTradeWithdrawStatus} or {@code null}
   */
  public static BitcoinTradeWithdrawStatus from(String withdrawStatus) {

    for (BitcoinTradeWithdrawStatus withdrawStatusType : values()) {
      if (withdrawStatusType.getWithdrawStatus().equals(withdrawStatus)) {
        return withdrawStatusType;
      }
    }
    return null;
  }
}
