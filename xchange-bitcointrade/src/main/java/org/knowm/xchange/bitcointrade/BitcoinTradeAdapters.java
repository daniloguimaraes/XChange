package org.knowm.xchange.bitcointrade;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWalletBalance;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWalletBalanceResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeDepositListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWithdrawListResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradePublicTrade;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTicker;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTickerResponse;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.utils.DateUtils;

/**
 * BitcoinTrade adapter class.
 *
 * @author Danilo Guimaraes
 */
public final class BitcoinTradeAdapters {

  private BitcoinTradeAdapters() {
    // Static use only
  }

  public static Ticker adaptBitcoinTradeTicker(
      BitcoinTradeTickerResponse bitcointradeTickerResponse, CurrencyPair currencyPair) {

    return adaptBitcoinTradeTicker(bitcointradeTickerResponse.getData(), currencyPair);
  }

  static Ticker adaptBitcoinTradeTicker(BitcoinTradeTicker ticker, CurrencyPair currencyPair) {

    BigDecimal last = ticker.getLast();
    BigDecimal bid = ticker.getBuy();
    BigDecimal ask = ticker.getSell();
    BigDecimal high = ticker.getHigh();
    BigDecimal low = ticker.getLow();
    BigDecimal volume = ticker.getVolume();
    Date date = fromISODateStringQuietly(ticker.getDate());

    return new Ticker.Builder()
        .instrument(currencyPair)
        .last(last)
        .bid(bid)
        .ask(ask)
        .high(high)
        .low(low)
        .volume(volume)
        .timestamp(date)
        .build();
  }

  public static OrderBook adaptBitcoinTradeOrderBook(
      BitcoinTradeOrderBook bitcointradeOrderBook, CurrencyPair currencyPair) {

    List<LimitOrder> asks =
        adaptBitcoinTradePublicOrders(bitcointradeOrderBook.getAsks(), OrderType.ASK, currencyPair);
    List<LimitOrder> bids =
        adaptBitcoinTradePublicOrders(bitcointradeOrderBook.getBids(), OrderType.BID, currencyPair);

    return new OrderBook(null, asks, bids);
  }

  static List<LimitOrder> adaptBitcoinTradePublicOrders(
      List<BitcoinTradeOrderBook.BidsAndAsks> list,
      OrderType orderType,
      CurrencyPair currencyPair) {

    List<LimitOrder> orders = new ArrayList<>();

    for (BitcoinTradeOrderBook.BidsAndAsks bidAsk : list) {
      LimitOrder limitOrder =
          new LimitOrder.Builder(orderType, currencyPair)
              .limitPrice(bidAsk.getUnitPrice())
              .originalAmount(bidAsk.getAmount())
              .id(bidAsk.getCode())
              .build();
      orders.add(limitOrder);
    }

    return orders;
  }

  public static Trades adaptBitcoinTradePublicTrades(
      BitcoinTradePublicTradeResponse bitcointradePublicTradeResponse, CurrencyPair currencyPair) {

    List<Trade> trades = new ArrayList<>();

    if (bitcointradePublicTradeResponse != null) {

      if (bitcointradePublicTradeResponse.getMessage() != null) {
        // When 'message' field is not null, probably there was an error.
        return new Trades(trades);
      }

      if (bitcointradePublicTradeResponse.getData() != null) {
        for (BitcoinTradePublicTrade bitcointradeTrade :
            bitcointradePublicTradeResponse.getData().getTrades()) {
          trades.add(adaptBitcoinTradePublicTrade(bitcointradeTrade, currencyPair));
        }
      }
    }

    return new Trades(trades, TradeSortType.SortByTimestamp);
  }

  private static Trade adaptBitcoinTradePublicTrade(
      BitcoinTradePublicTrade bitcointradeTrade, CurrencyPair currencyPair) {

    OrderType type = BitcoinTradeOrderType.from(bitcointradeTrade.getType());
    Date timestamp = fromISODateStringQuietly(bitcointradeTrade.getDate());

    return new Trade.Builder()
        .type(type)
        .originalAmount(bitcointradeTrade.getAmount())
        .instrument(currencyPair)
        .price(bitcointradeTrade.getUnitPrice())
        .timestamp(timestamp)
        .id(bitcointradeTrade.getActiveOrderCode())
        .build();
  }

  private static Date fromISODateStringQuietly(String orderDate) {

    try {
      return DateUtils.fromISODateString(orderDate);
    } catch (InvalidFormatException e) {
      return null;
    }
  }

  public static OpenOrders adaptBitcoinTradeOpenOrders(
      BitcoinTradeUserOrdersResponse bitcointradeUserOrdersResponse) {

    return null;
  }

  public static List<FundingRecord> adaptFundingRecords(
      BitcoinTradeDepositListResponse deposits, BitcoinTradeWithdrawListResponse withdrawals) {

    return null;
  }

  public static AccountInfo adaptAccountInfo(
      BitcoinTradeWalletBalanceResponse bitcoinTradeWalletBalanceResponse) {

    final List<Balance> balances = new ArrayList<>();

    for (BitcoinTradeWalletBalance bitcoinTradeWalletBalance :
        bitcoinTradeWalletBalanceResponse.getData()) {
      Balance balance =
          Balance.Builder.from(Balance.zero(bitcoinTradeWalletBalance.getCurrencyCode()))
              .available(bitcoinTradeWalletBalance.getAvailableAmount())
              .frozen(bitcoinTradeWalletBalance.getLockedAmount())
              .build();

      balances.add(balance);
    }

    return new AccountInfo(Wallet.Builder.from(balances).build());
  }
}
