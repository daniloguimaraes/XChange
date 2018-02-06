package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointrade.Bitcointrade;
import org.knowm.xchange.bitcointrade.BitcointradeOrderStatus;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.service.trade.params.orders.BitcointradeOpenOrdersParamCurrencyPair;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

/**
 * @author Danilo Guimaraes
 */
public class BitcointradeTradeServiceRaw extends BitcointradeBasePollingService {

  /**
   * Constructor
   *
   * @param exchange the Bitcointrade Exchange
   */
  BitcointradeTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public boolean cancel(String orderId) throws IOException {
    return Boolean.parseBoolean(bitcointradeAuthenticated.cancelOrder(apiToken, orderId));
  }

  public BitcointradeUserOrdersResponse returnOpenOrders(OpenOrdersParams params) throws IOException {
    BitcointradeOpenOrdersParamCurrencyPair bitcointradeOpenOrdersParamCurrencyPair = null;

    if (params != null && params instanceof BitcointradeOpenOrdersParamCurrencyPair) {
      bitcointradeOpenOrdersParamCurrencyPair = (BitcointradeOpenOrdersParamCurrencyPair) params;
    }

    BitcointradeOrderStatus status = BitcointradeOrderStatus.WAITING;

    String startTime = null;
    String endTime = null;
    String currency = Currency.BTC.toString();
    String type = null;
    Integer pageSize = Bitcointrade.DEFAULT_PAGE_SIZE;
    Integer currentPage = Bitcointrade.DEFAULT_CURRENT_PAGE;

    if (bitcointradeOpenOrdersParamCurrencyPair != null) {
      startTime = bitcointradeOpenOrdersParamCurrencyPair.getStartTime();
      endTime = bitcointradeOpenOrdersParamCurrencyPair.getEndTime();
      currency = bitcointradeOpenOrdersParamCurrencyPair.getCurrencyPair().base.toString();
      type = bitcointradeOpenOrdersParamCurrencyPair.getType();
      pageSize = bitcointradeOpenOrdersParamCurrencyPair.getPageSize();
      currentPage = bitcointradeOpenOrdersParamCurrencyPair.getCurrentPage();
    }

    return bitcointradeAuthenticated.getUserOrders(apiToken, status, startTime, endTime, currency, type, pageSize, currentPage);
  }
}
