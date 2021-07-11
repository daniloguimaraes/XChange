package org.knowm.xchange.bitcointrade.service;

import static org.knowm.xchange.bitcointrade.BitcoinTradeConstants.DEFAULT_CURRENT_PAGE;
import static org.knowm.xchange.bitcointrade.BitcoinTradeConstants.DEFAULT_PAGE_SIZE;
import static org.knowm.xchange.bitcointrade.BitcoinTradeCurrencyPairNormalizer.normalize;

import java.io.IOException;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderStatus;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.service.trade.params.orders.BitcoinTradeOpenOrdersParamCurrencyPair;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

/** @author Danilo Guimaraes */
public class BitcoinTradeTradeServiceRaw extends BitcoinTradeBasePollingService {

  /**
   * Constructor
   *
   * @param exchange the BitcoinTrade Exchange
   */
  BitcoinTradeTradeServiceRaw(BitcoinTradeExchange exchange) {

    super(exchange);
  }

  public boolean cancel(String orderId) throws IOException {
    return Boolean.parseBoolean(bitcointradeAuthenticated.cancelOrder(apiToken, orderId));
  }

  public BitcoinTradeUserOrdersResponse returnOpenOrders(OpenOrdersParams params)
      throws IOException {
    BitcoinTradeOpenOrdersParamCurrencyPair bitcointradeOpenOrdersParamCurrencyPair = null;

    if (params instanceof BitcoinTradeOpenOrdersParamCurrencyPair) {
      bitcointradeOpenOrdersParamCurrencyPair = (BitcoinTradeOpenOrdersParamCurrencyPair) params;
    }

    BitcoinTradeOrderStatus status = BitcoinTradeOrderStatus.WAITING;

    String startTime = null;
    String endTime = null;
    String currency = normalize(CurrencyPair.BTC_BRL);
    String type = null;
    Integer pageSize = DEFAULT_PAGE_SIZE;
    Integer currentPage = DEFAULT_CURRENT_PAGE;

    if (bitcointradeOpenOrdersParamCurrencyPair != null) {
      startTime = bitcointradeOpenOrdersParamCurrencyPair.getStartTime();
      endTime = bitcointradeOpenOrdersParamCurrencyPair.getEndTime();
      currency = bitcointradeOpenOrdersParamCurrencyPair.getCurrencyPair().base.toString();
      type = bitcointradeOpenOrdersParamCurrencyPair.getType();
      pageSize = bitcointradeOpenOrdersParamCurrencyPair.getPageSize();
      currentPage = bitcointradeOpenOrdersParamCurrencyPair.getCurrentPage();
    }

    return bitcointradeAuthenticated.getUserOrders(
        apiToken, status, startTime, endTime, currency, type, pageSize, currentPage);
  }
}
