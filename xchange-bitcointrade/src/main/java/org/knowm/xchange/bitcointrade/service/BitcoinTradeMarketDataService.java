package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.bitcointrade.BitcoinTradeAdapters;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeOrderBookResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTickerResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * {@link MarketDataService} implementation for BitcoinTrade Exchange.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeMarketDataService extends BitcoinTradeMarketDataServiceRaw
    implements MarketDataService {

  /**
   * Constructor
   *
   * @param exchange the BitcoinTrade Exchange
   */
  public BitcoinTradeMarketDataService(BitcoinTradeExchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

    BitcoinTradeTickerResponse bitcointradeTickerResponse = getBitcoinTradeTicker(currencyPair);
    return BitcoinTradeAdapters.adaptBitcoinTradeTicker(bitcointradeTickerResponse, currencyPair);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args)
      throws ExchangeException, IOException {

    BitcoinTradeOrderBookResponse ob = getBitcoinTradeOrderBook();
    return BitcoinTradeAdapters.adaptBitcoinTradeOrderBook(ob.getData(), currencyPair);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args)
      throws ExchangeException, IOException {

    String startTime = null;
    String endTime = null;
    Integer pageSize = null;
    Integer currentPage = null;

    if (args != null) {
      switch (args.length) {
        case 4:
          if (args[3] != null && args[3] instanceof Integer) {
            currentPage = (Integer) args[4];
          }
        case 3:
          if (args[2] != null && args[2] instanceof Integer) {
            pageSize = (Integer) args[2];
          }
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
    BitcoinTradePublicTradeResponse bitcointradePublicTradeResponse;
    if (startTime == null && endTime == null) {
      bitcointradePublicTradeResponse = getBitcoinTradePublicTrades(currencyPair);
    } else {
      bitcointradePublicTradeResponse =
          getBitcoinTradePublicTrades(currencyPair, startTime, endTime, pageSize, currentPage);
    }
    return BitcoinTradeAdapters.adaptBitcoinTradePublicTrades(
        bitcointradePublicTradeResponse, currencyPair);
  }

  /**
   * Get a {@code currencyPair} estimated price at BitcoinTrade Exchange.
   *
   * @param amount the amount
   * @param pair the currency pair (eg. BRLBTC)
   * @param type the type (buy or sell)
   * @return the currency pair price or {@code null}
   */
  public BigDecimal getEstimatedPrice(BigDecimal amount, String pair, String type) {
    return estimatedPrice(amount, pair, type);
  }
}
