package org.knowm.xchange.bitcointrade.service.trade.params.orders;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParamCurrencyPair;

/**
 * @author Danilo Guimar&atilde;es
 */
public class BitcointradeOpenOrdersParamCurrencyPair implements OpenOrdersParamCurrencyPair {

  private final CurrencyPair pair;
  private final String startTime;
  private final String endTime;
  private final String type;
  private final Integer pageSize;
  private final Integer currentPage;

  private BitcointradeOpenOrdersParamCurrencyPair(CurrencyPair pair, String startTime, String endTime, String type, Integer pageSize, Integer
      currentPage) {
    this.pair = pair;
    this.startTime = startTime;
    this.endTime = endTime;
    this.type = type;
    this.pageSize = pageSize;
    this.currentPage = currentPage;
  }

  @Override
  public CurrencyPair getCurrencyPair() {

    return pair;
  }

  @Override
  public void setCurrencyPair(CurrencyPair pair) {

  }

  public String getStartTime() {

    return startTime;
  }


  public String getEndTime() {

    return endTime;
  }


  public String getType() {
    return type;
  }

  public Integer getPageSize() {
    return pageSize;
  }


  public Integer getCurrentPage() {
    return currentPage;
  }

  /**
   * Internal BitcointradeOpenOrdersParamCurrencyPai builder
   */
  public static class Builder {

    private final CurrencyPair currencyPair;
    private String startTime;
    private String endTime;
    private String type;
    private Integer pageSize;
    private Integer currentPage;

    // Prevent repeat builds
    private boolean isBuilt = false;

    /**
     *
     * @param currencyPair
     */
    Builder(CurrencyPair currencyPair) {

      this.currencyPair = currencyPair;
    }

    public BitcointradeOpenOrdersParamCurrencyPair build() {

      validateState();

      BitcointradeOpenOrdersParamCurrencyPair orderParams = new BitcointradeOpenOrdersParamCurrencyPair(currencyPair, startTime, endTime, type,
          pageSize, currentPage);

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

    Builder type(String type) {

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
