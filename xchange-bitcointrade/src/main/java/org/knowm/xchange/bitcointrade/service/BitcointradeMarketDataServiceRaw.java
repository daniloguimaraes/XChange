package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointrade.BitcointradeException;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBookResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponse;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

/**
 * MarketDataService raw implementation for Bitcointrade Exchange.
 *
 * @author Danilo Guimaraes
 */
class BitcointradeMarketDataServiceRaw extends BitcointradeBasePollingService {

  private static final int DEFAULT_PAGE_SIZE = 200;
  private static final int DEFAULT_CURRENT_PAGE = 1;

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  BitcointradeMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
  }

  /**
   * Get the Bitcointrade Exchange ticker
   *
   * @param currencyPair the currency pair
   * @return an instance of {@link BitcointradeTickerResponse}
   * @throws ExchangeException
   */
  BitcointradeTickerResponse getBitcointradeTicker(CurrencyPair currencyPair) throws ExchangeException {

    try {
      return bitcointrade.getTicker(currencyPair.base.toString());
    } catch (BitcointradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  /**
   * Get the BTC order book at Bitcointrade Exchange.
   *
   * <p>
   * To get a different currency order book, use {@link #getBitcointradeOrderBook(Currency)}.
   * </p>
   *
   * @return an instance of {@link BitcointradeOrderBookResponse}
   * @throws IOException
   */
  BitcointradeOrderBookResponse getBitcointradeOrderBook() throws IOException {

      return getBitcointradeOrderBook(Currency.BTC);
  }

  /**
   * Get a specific currency order book at Bitcointrade Exchange.
   *
   * @param currency the order book currency
   * @return an instance of {@link BitcointradeOrderBookResponse}
   * @throws IOException
   */
  BitcointradeOrderBookResponse getBitcointradeOrderBook(Currency currency) throws IOException {

    try {
      return bitcointrade.getOrderBook(currency.toString());
    } catch (BitcointradeException e) {
      throw new ExchangeException(e.getError(), e);
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage(), e);
    }
  }

  /**
   * List all public trades made at Bitcointrade Exchange.
   *
   * @param currencyPair the trade currency pair
   * @return an instance of {@link BitcointradePublicTradeResponse}
   * @throws ExchangeException
   */
  BitcointradePublicTradeResponse getBitcointradePublicTrades(CurrencyPair currencyPair) throws ExchangeException {

      return getBitcointradePublicTrades(currencyPair, null, null);
  }

  /**
   * List all public trades made at Bitcointrade Exchange.
   *
   * @param currencyPair the trade currency pair
   * @param startTime the oldest trade filter
   * @param endTime the newest trade filter
   * @return an instance of {@link BitcointradePublicTradeResponse}
   * @throws ExchangeException
   */
  BitcointradePublicTradeResponse getBitcointradePublicTrades(CurrencyPair currencyPair, String startTime, String endTime) throws ExchangeException {

    return getBitcointradePublicTrades(currencyPair, startTime, endTime, DEFAULT_PAGE_SIZE, DEFAULT_CURRENT_PAGE);
  }

  /**
   * List all public trades made at Bitcointrade Exchange.
   *
   * @param currencyPair the trade currency pair
   * @param startTime the oldest trade filter
   * @param endTime the newest trade filter
   * @param pageSize the number of items on the page
   * @param currentPage the current page
   * @return an instance of {@link BitcointradePublicTradeResponse}
   * @throws ExchangeException
   */
  BitcointradePublicTradeResponse getBitcointradePublicTrades(CurrencyPair currencyPair, String startTime, String endTime, Integer pageSize,
        Integer currentPage) throws ExchangeException {

    String currency = currencyPair.base.toString();

    try {
      return bitcointrade.getTrades(currency, startTime, endTime, pageSize, currentPage);
    } catch (BitcointradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

}
