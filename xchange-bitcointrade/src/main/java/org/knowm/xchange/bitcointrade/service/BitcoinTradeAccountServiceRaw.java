package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.bitcointrade.BitcoinTradeException;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.bitcointrade.BitcoinTradeFeeType;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeDepositListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWithdrawListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWithdrawResponse;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

/** @author Danilo Guimaraes */
public class BitcoinTradeAccountServiceRaw extends BitcoinTradeBasePollingService {

  /**
   * Constructor
   *
   * @param exchange the BitcoinTrade Exchange
   */
  BitcoinTradeAccountServiceRaw(BitcoinTradeExchange exchange) {

    super(exchange);
  }

  String withdraw(String destination, BigDecimal amount) throws ExchangeException {
    return withdraw("btc", destination, amount, null, BitcoinTradeFeeType.REGULAR);
  }

  String withdraw(String destination, BigDecimal amount, BigDecimal fee) throws ExchangeException {
    return withdraw("btc", destination, amount, fee, null);
  }

  String withdraw(String currency, String destination, BigDecimal amount, BigDecimal fee,
      BitcoinTradeFeeType feeType)
      throws ExchangeException {

    try {
      final BitcoinTradeWithdrawResponse withdrawResponse =
          bitcointradeAuthenticated.withdraw(
              apiToken, currency, null, null, feeType, amount, destination);

      if (withdrawResponse != null && withdrawResponse.getData() != null) {
        return withdrawResponse.getData().getCode();
      }
    } catch (BitcoinTradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
    return null;
  }

  BitcoinTradeWithdrawListResponse withdrawals(TradeHistoryParams params) {
    try {
      return bitcointradeAuthenticated.getWithdrawalsList(
          apiToken, "btc", null, null, null, null, null);
    } catch (BitcoinTradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  BitcoinTradeDepositListResponse deposits(TradeHistoryParams params) {
    try {
      return bitcointradeAuthenticated.getDepositList(apiToken, null, null, null, null, null, null);
    } catch (BitcoinTradeException e) {
      throw new ExchangeException(e.getError());
    } catch (IOException e) {
      throw new ExchangeException(e.getMessage());
    }
  }
}
