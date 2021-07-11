package org.knowm.xchange.bitcointrade;

import org.knowm.xchange.dto.Order;

/**
 * BitcoinTrade Exchange order type
 *
 * @author Danilo Guimaraes
 */
public enum BitcoinTradeOrderType {
  BUY("buy"),
  SELL("sell");

  private final String type;

  BitcoinTradeOrderType(String type) {

    this.type = type;
  }

  public String getOrderType() {

    return type;
  }

  /**
   * Create an instance of {@link BitcoinTradeOrderType} based on the String.
   *
   * @param type the order type
   * @return an instance of {@link BitcoinTradeOrderType} or {@code null}
   */
  public static BitcoinTradeOrderType from(String type) {

    for (BitcoinTradeOrderType subtypeEnum : values()) {
      if (subtypeEnum.getOrderType().equals(type)) {
        return subtypeEnum;
      }
    }
    return null;
  }

  /**
   * Create an instance of {@link Order.OrderType} based on {@link BitcoinTradeOrderType}.
   *
   * @param orderType the order type
   * @return an {@link Order.OrderType} or {@code null}
   */
  public static Order.OrderType from(BitcoinTradeOrderType orderType) {

    if (orderType != null) {
      return orderType.getOrderType().equalsIgnoreCase("buy")
          ? Order.OrderType.BID
          : Order.OrderType.ASK;
    }
    return null;
  }
}
