package org.knowm.xchange.bitcointrade;

import org.knowm.xchange.dto.Order;

/**
 * Bitcointrade Exchange order type
 *
 * @author Danilo Guimaraes
 */
public enum BitcointradeOrderType {

  BUY("buy"),
  SELL("sell");

  private final String type;

  BitcointradeOrderType(String type) {

    this.type = type;
  }

  public String getOrderType() {

    return type;
  }

  /**
   * Create an instance of {@link BitcointradeOrderType} based on the String.
   *
   * @param type the order type
   * @return an instance of {@link BitcointradeOrderType} or {@code null}
   */
  public static BitcointradeOrderType from(String type) {

    for (BitcointradeOrderType subtypeEnum : values()) {
      if (subtypeEnum.getOrderType().equals(type)) {
        return subtypeEnum;
      }
    }
    return null;
  }

  /**
   * Create an instance of {@link Order.OrderType} based on {@link BitcointradeOrderType}.
   *
   * @param orderType the order type
   * @return an {@link Order.OrderType} or {@code null}
   */
  public static Order.OrderType from(BitcointradeOrderType orderType) {

    if (orderType != null) {
      return orderType.getOrderType().equalsIgnoreCase("buy") ? Order.OrderType.BID : Order.OrderType.ASK;
    }
    return null;
  }
}

