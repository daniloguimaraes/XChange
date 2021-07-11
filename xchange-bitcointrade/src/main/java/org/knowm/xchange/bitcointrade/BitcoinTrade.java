package org.knowm.xchange.bitcointrade;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeOrderBook;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeOrderBookResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradePublicTradeResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeTickerResponse;

/**
 * Bitcointrade Exchange public end-points.
 *
 * @author Danilo Guimaraes
 */
@Path("v3/public/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitcoinTrade {

  /**
   * Get the Bitcointrade Exchange ticker
   *
   * @param currencyPair the currency pair (eg. BRLBTC)
   * @return an instance of {@link BitcoinTradeTickerResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currencyPair}/ticker")
  BitcoinTradeTickerResponse getTicker(@PathParam("currencyPair") String currencyPair)
      throws BitcoinTradeException, IOException;

  /**
   * Get the public order book at Bitcointrade Exchange
   *
   * @param currencyPair the currency pair (eg. BRLBTC)
   * @return an instance of {@link BitcoinTradeOrderBook}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currencyPair}/orders")
  BitcoinTradeOrderBookResponse getOrderBook(@PathParam("currencyPair") String currencyPair)
      throws BitcoinTradeException, IOException;

  /**
   * List all public trades made at Bitcointrade Exchange.
   *
   * @param currencyPair the currency pair (eg. BRLBTC). Mandatory
   * @param startTime start trade time, in ISO-8601 date/time format. Optional
   * @param endTime end trade time, in ISO-8601 date/time format. Optional
   * @param pageSize the page size. Default: 20. Maximum: 250. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @return an array of {@link BitcoinTradePublicTradeResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currencyPair}/trades")
  BitcoinTradePublicTradeResponse getTrades(
      @PathParam("currencyPair") String currencyPair,
      @QueryParam("start_time") String startTime,
      @QueryParam("end_time") String endTime,
      @QueryParam("page_size") Integer pageSize,
      @QueryParam("current_page") Integer currentPage)
      throws BitcoinTradeException, IOException;
}
