package org.knowm.xchange.bitcointrade.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitcointrade.BitcointradeException;
import org.knowm.xchange.bitcointrade.BitcointradeFeeType;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeDepositListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeWithdrawListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeWithdrawResponse;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 *
 * @author Danilo Guimaraes
 */
public class BitcointradeAccountServiceRaw extends BitcointradeBasePollingService {

    /**
     * Constructor
     *
     * @param exchange the Bitcointrade Exchange
     */
    BitcointradeAccountServiceRaw(Exchange exchange) {

        super(exchange);
    }

    String withdraw(String address, BigDecimal amount) throws ExchangeException {
        return withdraw(address, amount, null, BitcointradeFeeType.REGULAR);
    }

    String withdraw(String address, BigDecimal amount, BigDecimal fee) throws ExchangeException {
        return withdraw(address, amount, fee, null);
    }

    String withdraw(String address, BigDecimal amount, BigDecimal fee, BitcointradeFeeType feeType) throws ExchangeException {

        try {
            final BitcointradeWithdrawResponse withdrawResponse =
                    bitcointradeAuthenticated.withdraw(apiToken, address, fee, feeType, amount);

            if (withdrawResponse != null && withdrawResponse.getData() != null) {
                return withdrawResponse.getData().getCode();
            }
        } catch (BitcointradeException e) {
            throw new ExchangeException(e.getError());
        } catch (IOException e) {
            throw new ExchangeException(e.getMessage());
        }
        return null;
    }

    BitcointradeWithdrawListResponse withdrawals(TradeHistoryParams params) {
        try {
            return bitcointradeAuthenticated.getWithdrawList(apiToken, null, null, null, null, null);
        } catch (BitcointradeException e) {
            throw new ExchangeException(e.getError());
        } catch (IOException e) {
            throw new ExchangeException(e.getMessage());
        }
    }

    BitcointradeDepositListResponse deposits(TradeHistoryParams params) {
        try {
            return bitcointradeAuthenticated.getDepositList(apiToken, null, null, null, null, null);
        } catch (BitcointradeException e) {
            throw new ExchangeException(e.getError());
        } catch (IOException e) {
            throw new ExchangeException(e.getMessage());
        }
    }



}
