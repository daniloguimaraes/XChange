package org.knowm.xchange.bitcointrade.service;

import org.knowm.xchange.service.trade.params.CancelOrderByIdParams;

/**
 * @author Danilo Guimar&atilde;es
 */
public class BitcointradeCancelOrderParams implements CancelOrderByIdParams {
  private final String orderId;

  public BitcointradeCancelOrderParams(String orderId) {
    this.orderId = orderId;
  }

  @Override
  public String getOrderId() {
    return orderId;
  }
}