package org.knowm.xchange.bitcointrade.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.knowm.xchange.bitcointrade.BitcoinTradeAdapters;
import org.knowm.xchange.bitcointrade.BitcoinTradeExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamCurrency;
import org.knowm.xchange.service.trade.params.DefaultWithdrawFundsParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

/**
 * {@link AccountService} implementation for BitcoinTrade Exchange.
 *
 * @author Danilo Guimaraes
 */
public class BitcoinTradeAccountService extends BitcoinTradeAccountServiceRaw
    implements AccountService {

  /**
   * Constructor
   *
   * @param exchange the BitcoinTrade Exchange
   */
  BitcoinTradeAccountService(BitcoinTradeExchange exchange) {

    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {

    throw new NotAvailableFromExchangeException();
  }

  @Override
  public String withdrawFunds(Currency currency, BigDecimal amount, String address)
      throws IOException {

    if (!Currency.BTC.equals(currency)) {
      throw new NotAvailableFromExchangeException();
    }
    return withdraw(address, amount);
  }

  @Override
  public String withdrawFunds(WithdrawFundsParams params) throws IOException {

    if (params != null && params instanceof DefaultWithdrawFundsParams) {
      DefaultWithdrawFundsParams defaultParams = (DefaultWithdrawFundsParams) params;
      return withdraw(defaultParams.address, defaultParams.amount, defaultParams.commission);
    }
    return null;
  }

  @Override
  public String requestDepositAddress(Currency currency, String... args) throws IOException {

    return null;
  }

  @Override
  public TradeHistoryParams createFundingHistoryParams() {

    return new DefaultTradeHistoryParamCurrency();
  }

  @Override
  public List<FundingRecord> getFundingHistory(TradeHistoryParams params) throws IOException {

    return BitcoinTradeAdapters.adaptFundingRecords(deposits(params), withdrawals(params));
  }
}
