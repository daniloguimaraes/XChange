package org.knowm.xchange.bitcointrade.service;

import org.knowm.xchange.Exchange;

/**
 * @author Danilo Guimaraes
 * @since 29/01/2018
 */
public class BitcointradeTradeServiceRaw extends BitcointradeBasePollingService {

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  BitcointradeTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }
}
