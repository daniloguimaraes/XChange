package org.knowm.xchange.bitcointrade.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointrade.Bitcointrade;
import org.knowm.xchange.bitcointrade.BitcointradeAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;

import si.mazi.rescu.RestProxyFactory;

/**
 * Bitcointrade base polling service.
 *
 * <p>
 *   In the near future, when Bitcointrade launches trade API, this class will be responsible for managing credentials
 * </p>
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeBasePollingService extends BaseExchangeService implements BaseService {

  protected final Bitcointrade bitcointrade;
  protected final BitcointradeAuthenticated bitcointradeAuthenticated;
  protected final String apiToken;

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  BitcointradeBasePollingService(Exchange exchange) {

    super(exchange);

    this.apiToken = "ApiToken " + exchange.getExchangeSpecification().getSecretKey();
    this.bitcointrade = RestProxyFactory.createProxy(Bitcointrade.class, exchange.getExchangeSpecification().getSslUri());
    this.bitcointradeAuthenticated = RestProxyFactory.createProxy(BitcointradeAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
  }

}
