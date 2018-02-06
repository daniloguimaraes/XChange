package org.knowm.xchange.bitcointrade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.knowm.xchange.bitcointrade.dto.account.BitcointradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradePublicTrade;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTicker;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.utils.DateUtils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * Bitcointrade adapter class.
 *
 * @author Danilo Guimaraes
 */
public final class BitcointradeAdapters {

  private BitcointradeAdapters() {
    // Static use only
  }

  public static Ticker adaptBitcointradeTicker(BitcointradeTickerResponse bitcointradeTickerResponse, CurrencyPair currencyPair) {

    return adaptBitcointradeTicker(bitcointradeTickerResponse.getData(), currencyPair);
  }

  static Ticker adaptBitcointradeTicker(BitcointradeTicker ticker, CurrencyPair currencyPair) {

    BigDecimal last = ticker.getLast();
    BigDecimal bid = ticker.getBuy();
    BigDecimal ask = ticker.getSell();
    BigDecimal high = ticker.getHigh();
    BigDecimal low = ticker.getLow();
    BigDecimal volume = ticker.getVolume();
    Date date = fromISODateStringQuietly(ticker.getDate());

    return new Ticker.Builder().currencyPair(currencyPair).last(last).bid(bid).ask(ask).high(high).low(low).volume(volume)
        .timestamp(date).build();
  }

  public static OrderBook adaptBitcointradeOrderBook(BitcointradeOrderBook bitcointradeOrderBook, CurrencyPair currencyPair) {

    List<LimitOrder> asks = adaptBitcointradePublicOrders(bitcointradeOrderBook.getAsks(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids = adaptBitcointradePublicOrders(bitcointradeOrderBook.getBids(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  static List<LimitOrder> adaptBitcointradePublicOrders(List<BitcointradeOrderBook.BidsAndAsks> list, OrderType orderType, CurrencyPair
      currencyPair) {

    List<LimitOrder> orders = new ArrayList<>();

    for (BitcointradeOrderBook.BidsAndAsks bidAsk : list) {
      LimitOrder limitOrder = new LimitOrder.Builder(orderType, currencyPair)
          .limitPrice(bidAsk.getUnitPrice())
          .originalAmount(bidAsk.getAmount())
          .id(bidAsk.getCode())
          .build();
      orders.add(limitOrder);
    }

    return orders;
  }


  public static Trades adaptBitcointradePublicTrades(BitcointradePublicTradeResponse bitcointradePublicTradeResponse, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<>();

    if (bitcointradePublicTradeResponse != null) {

      if (bitcointradePublicTradeResponse.getMessage() != null) {
        // When 'message' field is not null, probably there was an error.
        return new Trades(trades);
      }

      if (bitcointradePublicTradeResponse.getData() != null) {
        for (BitcointradePublicTrade bitcointradeTrade : bitcointradePublicTradeResponse.getData().getTrades()) {
          trades.add(adaptBitcointradePublicTrade(bitcointradeTrade, currencyPair));
        }
      }
    }

    return new Trades(trades, TradeSortType.SortByTimestamp);
  }

  private static Trade adaptBitcointradePublicTrade(BitcointradePublicTrade bitcointradeTrade, CurrencyPair currencyPair) {

    OrderType type = BitcointradeOrderType.from(bitcointradeTrade.getType());
    Date timestamp = fromISODateStringQuietly(bitcointradeTrade.getDate());

    return new Trade(type, bitcointradeTrade.getAmount(), currencyPair, bitcointradeTrade.getUnitPrice(), timestamp,
        bitcointradeTrade.getActiveOrderCode());
  }

  private static Date fromISODateStringQuietly(String orderDate) {

    try {
      return DateUtils.fromISODateString(orderDate);
    } catch (InvalidFormatException e) {
      return null;
    }
  }

  public static OpenOrders adaptBitcointradeOpenOrders(BitcointradeUserOrdersResponse bitcointradeUserOrdersResponse) {

    return null;
  }
}
