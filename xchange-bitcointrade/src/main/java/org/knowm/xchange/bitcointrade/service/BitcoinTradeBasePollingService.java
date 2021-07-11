package org.knowm.xchange.bitcointrade.service;

import org.knowm.xchange.bitcointrade.BitcoinTrade;
import org.knowm.xchange.bitcointrade.BitcoinTradeAuthenticated;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.RestProxyFactory;

/**
 * Bitcointrade base polling service.
 *
 * <p>In the near future, when Bitcointrade launches trade API, this class will be responsible for
 * managing credentials
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeBasePollingService extends BaseExchangeService<BitcoinTradeExchange>
    implements BaseService {

  protected final BitcoinTrade bitcointrade;
  protected final BitcoinTradeAuthenticated bitcointradeAuthenticated;
  protected final String apiToken;

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  BitcoinTradeBasePollingService(BitcoinTradeExchange exchange) {

    super(exchange);

    this.apiToken = exchange.getExchangeSpecification().getSecretKey();
    this.bitcointrade =
        RestProxyFactory.createProxy(
            BitcoinTrade.class, exchange.getExchangeSpecification().getSslUri());
    this.bitcointradeAuthenticated =
        RestProxyFactory.createProxy(
            BitcoinTradeAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
  }
}
