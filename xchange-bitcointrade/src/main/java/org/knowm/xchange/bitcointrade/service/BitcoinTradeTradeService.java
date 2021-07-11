package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;
import java.util.Collection;
import org.knowm.xchange.bitcointrade.BitcoinTradeAdapters;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeUserOrdersResponse;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderByIdParams;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

/**
 * {@link TradeService} implementation for Bitcointrade Exchange.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeTradeService extends BitcoinTradeTradeServiceRaw implements TradeService {

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  BitcoinTradeTradeService(BitcoinTradeExchange exchange) {

    super(exchange);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {

    return getOpenOrders(null);
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams params) throws IOException {

    BitcoinTradeUserOrdersResponse bitcointradeUserOrdersResponse = returnOpenOrders(params);
    return BitcoinTradeAdapters.adaptBitcointradeOpenOrders(bitcointradeUserOrdersResponse);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {

    return null;
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {

    return null;
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {

    return cancel(orderId);
  }

  @Override
  public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {

    if (orderParams instanceof CancelOrderByIdParams) {
      return cancelOrder(((CancelOrderByIdParams) orderParams).getOrderId());
    }
    return false;
  }

  @Override
  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {

    return null;
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {

    return null;
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {

    return null;
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {

    // Bitcointrade Exchange API doesn't support listing orders by ID.
    throw new NotAvailableFromExchangeException();
  }
}
