package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointrade.BitcointradeAdapters;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * {@link MarketDataService} implementation for Bitcointrade Exchange.
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeMarketDataService extends BitcointradeMarketDataServiceRaw implements MarketDataService {

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  public BitcointradeMarketDataService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    BitcointradeTickerResponse bitcointradeTickerResponse = getBitcointradeTicker(currencyPair);
    return BitcointradeAdapters.adaptBitcointradeTicker(bitcointradeTickerResponse, currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    BitcointradeOrderBook ob = getBitcointradeOrderBook();
    return BitcointradeAdapters.adaptBitcointradeOrderBook(ob, currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws ExchangeException, IOException {

    String startTime = null;
    String endTime = null;

    if (args != null) {
      switch (args.length) {
      case 2:
        if (args[1] != null && args[1] instanceof String) {
          endTime = (String) args[1];
        }
      case 1:
        if (args[0] != null && args[0] instanceof String) {
          startTime = (String) args[0];
        }
      }
    }
    BitcointradePublicTradeResponse bitcointradePublicTradeResponse;
    if (startTime == null && endTime == null) {
      bitcointradePublicTradeResponse = getBitcointradePublicTrades(currencyPair);
    } else {
      bitcointradePublicTradeResponse = getBitcointradePublicTrades(currencyPair, startTime, endTime);
    }
    return BitcointradeAdapters.adaptBitcointradePublicTrades(bitcointradePublicTradeResponse, currencyPair);
  }

}
