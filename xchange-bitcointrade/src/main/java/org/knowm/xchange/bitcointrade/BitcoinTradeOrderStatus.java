package org.knowm.xchange.bitcointrade;

import org.knowm.xchange.dto.Order;

/**
 * BitcoinTrade Exchange order status.
 *
 * @author Danilo Guimaraes
 */
public enum BitcoinTradeOrderStatus {
  EXECUTED_COMPLETELY("executed_completely"),
  EXECUTED_PARTIALLY("executed_partially"),
  WAITING("waiting"),
  CANCELED("canceled");

  private final String orderStatus;

  BitcoinTradeOrderStatus(String orderStatus) {

    this.orderStatus = orderStatus;
  }

  public String getOrderStatus() {

    return orderStatus;
  }

  /**
   * Create an instance of {@link BitcoinTradeOrderStatus} based on a String.
   *
   * @param orderStatus the order status
   * @return an instance of {@link BitcoinTradeOrderStatus} or {@code null}
   */
  public static BitcoinTradeOrderStatus from(String orderStatus) {

    for (BitcoinTradeOrderStatus orderStatusType : values()) {
      if (orderStatusType.getOrderStatus().equals(orderStatus)) {
        return orderStatusType;
      }
    }
    return null;
  }

  /**
   * Create an instance of {@link Order.OrderStatus} based on {@link BitcoinTradeOrderStatus}.
   *
   * @param orderStatus an instance of {@link BitcoinTradeOrderStatus}
   * @return an instance of {@link Order.OrderStatus} or {@code null}.
   */
  public static Order.OrderStatus from(BitcoinTradeOrderStatus orderStatus) {

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
