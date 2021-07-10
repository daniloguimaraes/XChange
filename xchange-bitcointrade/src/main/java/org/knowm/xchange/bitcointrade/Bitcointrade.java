package org.knowm.xchange.bitcointrade;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeOrderBookResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeTickerResponse;

/**
 * Bitcointrade Exchange public end-points.
 *
 * @author Danilo Guimaraes
 */
@Path("v3/public/")
@Produces(MediaType.APPLICATION_JSON)
public interface Bitcointrade {

  Integer DEFAULT_PAGE_SIZE = 20;
  Integer DEFAULT_CURRENT_PAGE = 1;

  /**
   * Get the Bitcointrade Exchange ticker
   *
   * @param currencyPair the currency pair (eg. BRLBTC)
   * @return an instance of {@link BitcointradeTickerResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currencyPair}/ticker")
  BitcointradeTickerResponse getTicker(@PathParam("currencyPair") String currencyPair) throws BitcointradeException, IOException;

  /**
   * Get the public order book at Bitcointrade Exchange
   *
   * @param currencyPair the currency pair (eg. BRLBTC)
   * @return an instance of {@link BitcointradeOrderBook}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currencyPair}/orders")
  BitcointradeOrderBookResponse getOrderBook(@PathParam("currencyPair") String currencyPair) throws BitcointradeException, IOException;

  /**
   * List all public trades made at Bitcointrade Exchange.
   *
   * @param currencyPair the currency pair (eg. BRLBTC). Mandatory
   * @param startTime start trade time, in ISO-8601 date/time format. Optional
   * @param endTime end trade time, in ISO-8601 date/time format. Optional
   * @param pageSize the page size. Default: 20. Maximum: 250. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @return an array of {@link BitcointradePublicTradeResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currencyPair}/trades")
  BitcointradePublicTradeResponse getTrades(@PathParam("currencyPair") String currencyPair, @QueryParam("start_time") String startTime,
      @QueryParam("end_time") String endTime, @QueryParam("page_size") Integer pageSize, @QueryParam("current_page") Integer currentPage)
      throws BitcointradeException, IOException;
}
