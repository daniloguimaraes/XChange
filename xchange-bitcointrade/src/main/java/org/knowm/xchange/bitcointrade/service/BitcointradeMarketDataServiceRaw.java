package org.knowm.xchange.bitcointrade.service;

import static org.knowm.xchange.bitcointrade.BitcointradeConstants.DEFAULT_CURRENT_PAGE;
import static org.knowm.xchange.bitcointrade.BitcointradeConstants.DEFAULT_PAGE_SIZE;
import static org.knowm.xchange.bitcointrade.BitcointradeCurrencyPairNormalizer.normalize;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointrade.BitcointradeException;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeEstimatedPriceResponse;
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
      return bitcointrade.getTicker(currencyPair.counter.toString().concat(currencyPair.base.toString()));
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
   * To get a different currency order book, use {@link #getBitcointradeOrderBook(CurrencyPair)}.
   * </p>
   *
   * @return an instance of {@link BitcointradeOrderBookResponse}
   * @throws IOException
   */
  BitcointradeOrderBookResponse getBitcointradeOrderBook() throws IOException {

      return getBitcointradeOrderBook(CurrencyPair.BTC_BRL);
  }

  /**
   * Get a specific currency order book at Bitcointrade Exchange.
   *
   * @param currencyPair the order book currency pair
   * @return an instance of {@link BitcointradeOrderBookResponse}
   * @throws IOException
   */
  BitcointradeOrderBookResponse getBitcointradeOrderBook(CurrencyPair currencyPair) throws IOException {

    try {
      return bitcointrade.getOrderBook(normalize(currencyPair));
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

    try {
      return bitcointrade.getTrades(normalize(currencyPair), startTime, endTime, pageSize, currentPage);
    } catch (BitcointradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  /**
   * Get the Bitcoin (BTC) estimated price at Bitcointrade Exchange.
   *
   * @param amount the amount
   * @param type the type  (buy or sell)
   * @return the currency price or {@code null}
   * @throws ExchangeException
   */
  BigDecimal estimatedPrice(BigDecimal amount, String type) throws ExchangeException {
    return estimatedPrice(amount, Currency.BTC.toString(), type);
  }

  /**
   * Get a {@code currency} estimated price at Bitcointrade Exchange.
   *
   * @param amount the amount
   * @param currency the currency (eg. BTC)
   * @param type the type  (buy or sell)
   * @return the currency price or {@code null}
   * @throws ExchangeException
   */
  BigDecimal estimatedPrice(BigDecimal amount, String currency, String type) throws ExchangeException {
    try {
      final BitcointradeEstimatedPriceResponse response = bitcointradeAuthenticated.estimatedPrice(apiToken, amount, currency, type);

      if (response != null) {
        if (response.getData() != null) {
          return response.getData().getPrice();
        } else if (response.getMessage() != null) {
          throw new ExchangeException(response.getMessage().toString());
        }
      }

      return null;
    } catch (BitcointradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

}
