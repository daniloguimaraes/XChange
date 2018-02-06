package org.knowm.xchange.bitcointrade;

import org.knowm.xchange.dto.Order;

/**
 * Bitcointrade Exchange order status.
 *
 * @author Danilo Guimaraes
 */
public enum BitcointradeOrderStatus {

  EXECUTED_COMPLETELY("executed_completely"),
  EXECUTED_PARTIALLY("executed_partially"),
  WAITING("waiting"),
  CANCELED("canceled");

  private final String orderStatus;

  BitcointradeOrderStatus(String orderStatus) {

    this.orderStatus = orderStatus;
  }

  public String getOrderStatus() {

    return orderStatus;
  }

  /**
   * Create an instance of {@link BitcointradeOrderStatus} based on a String.
   *
   * @param orderStatus the order status
   * @return an instance of {@link BitcointradeOrderStatus} or {@code null}
   */
  public static BitcointradeOrderStatus from(String orderStatus) {

    for (BitcointradeOrderStatus orderStatusType : values()) {
      if (orderStatusType.getOrderStatus().equals(orderStatus)) {
        return orderStatusType;
      }
    }
    return null;
  }

  /**
   * Create an instance of {@link Order.OrderStatus} based on {@link BitcointradeOrderStatus}.
   *
   * @param orderStatus an instance of {@link BitcointradeOrderStatus}
   * @return an instance of {@link Order.OrderStatus} or {@code null}.
   */
  public static Order.OrderStatus from(BitcointradeOrderStatus orderStatus) {

    if (orderStatus != null) {
      switch (orderStatus) {
        case EXECUTED_COMPLETELY:
          return Order.OrderStatus.FILLED;
        case EXECUTED_PARTIALLY:
          return Order.OrderStatus.PARTIALLY_FILLED;
        case WAITING:
          return Order.OrderStatus.NEW;
        case CANCELED:
          return Order.OrderStatus.CANCELED;
        default:
          return null;
      }
    }
    return null;
  }
}
