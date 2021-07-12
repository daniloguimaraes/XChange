package org.knowm.xchange.bitcointrade.service.trade.params.orders;

import org.knowm.xchange.bitcointrade.BitcoinTradeOrderSubtype;
import org.knowm.xchange.bitcointrade.BitcoinTradeOrderType;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParamCurrencyPair;

/** @author Danilo Guimar&atilde;es */
public class BitcoinTradeOpenOrdersParamCurrencyPair implements OpenOrdersParamCurrencyPair {

  private final CurrencyPair pair;
  private final String startTime;
  private final String endTime;
  private final BitcoinTradeOrderType type;
  private final BitcoinTradeOrderSubtype subtype;
  private final Integer pageSize;
  private final Integer currentPage;

  private BitcoinTradeOpenOrdersParamCurrencyPair(
      CurrencyPair pair,
      String startTime,
      String endTime,
      BitcoinTradeOrderType type,
      BitcoinTradeOrderSubtype subtype,
      Integer pageSize,
      Integer currentPage) {
    this.pair = pair;
    this.startTime = startTime;
    this.endTime = endTime;
    this.type = type;
    this.subtype = subtype;
    this.pageSize = pageSize;
    this.currentPage = currentPage;
  }

  @Override
  public CurrencyPair getCurrencyPair() {

    return pair;
  }

  @Override
  public void setCurrencyPair(CurrencyPair pair) {}

  public String getStartTime() {

    return startTime;
  }

  public String getEndTime() {

    return endTime;
  }

  public BitcoinTradeOrderType getType() {
    return type;
  }

  public BitcoinTradeOrderSubtype getSubtype() {
    return subtype;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  /** Internal BitcoinTradeOpenOrdersParamCurrencyPai builder */
  public static class Builder {

    private final CurrencyPair currencyPair;
    private String startTime;
    private String endTime;
    private BitcoinTradeOrderType type;
    private BitcoinTradeOrderSubtype subtype;
    private Integer pageSize;
    private Integer currentPage;

    // Prevent repeat builds
    private boolean isBuilt = false;

    /** @param currencyPair */
    Builder(CurrencyPair currencyPair) {

      this.currencyPair = currencyPair;
    }

    public BitcoinTradeOpenOrdersParamCurrencyPair build() {

      validateState();

      BitcoinTradeOpenOrdersParamCurrencyPair orderParams =
          new BitcoinTradeOpenOrdersParamCurrencyPair(
              currencyPair, startTime, endTime, type, subtype, pageSize, currentPage);

      isBuilt = true;

      return orderParams;
    }

    private void validateState() {

      if (isBuilt) {
        throw new IllegalStateException("The entity has been built");
      }
    }

    Builder startTime(String startTime) {

      this.startTime = startTime;
      return this;
    }

    Builder endTime(String endTime) {

      this.endTime = endTime;
      return this;
    }

    Builder type(BitcoinTradeOrderType type) {

      this.type = type;
      return this;
    }

    Builder pageSize(Integer pageSize) {

      this.pageSize = pageSize;
      return this;
    }

    Builder currentPage(Integer currentPage) {

      this.currentPage = currentPage;
      return this;
    }
  }
}
