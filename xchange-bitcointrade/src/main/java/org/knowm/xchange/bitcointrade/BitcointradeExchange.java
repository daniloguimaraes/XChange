package org.knowm.xchange.bitcointrade;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitcointrade.service.BitcointradeMarketDataService;
import org.knowm.xchange.utils.nonce.AtomicLongIncrementalTime2013NonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

/**
 * The Bitcointrade Exchange represantation itself.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeExchange extends BaseExchange implements Exchange {

  private final SynchronizedValueFactory<Long> nonceFactory = new AtomicLongIncrementalTime2013NonceFactory();

  @Override
  protected void initServices() {

    this.marketDataService = new BitcointradeMarketDataService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.bitcointrade.com.br/");
    exchangeSpecification.setHost("www.bitcointrade.com.br");
    exchangeSpecification.setPort(443);
    exchangeSpecification.setExchangeName("Bitcointrade");
    exchangeSpecification.setExchangeDescription("Bitcointrade is a Brazilian bitcoin exchange.");
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }
}
