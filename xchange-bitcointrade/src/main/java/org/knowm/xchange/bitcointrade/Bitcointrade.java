package org.knowm.xchange.bitcointrade;

import java.io.IOException;
import java.util.Map;

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
@Path("v1/public/")
@Produces(MediaType.APPLICATION_JSON)
public interface Bitcointrade {

  /**
   * Get the Bitcointrade Exchange ticker
   *
   * @return a {@link Map} containing an instance of {@link BitcointradeTickerResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currency}/ticker")
  BitcointradeTickerResponse getTicker(@PathParam("currency") String currency) throws BitcointradeException, IOException;

  /**
   * Get the public order book at Bitcointrade Exchange
   *
   * @return an instance of {@link BitcointradeOrderBook}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currency}/orders")
  BitcointradeOrderBookResponse getOrderBook(@PathParam("currency") String currency) throws BitcointradeException, IOException;

  /**
   * List all public trades made at Bitcointrade Exchange.
   *
   * @param currency the currency. BTC or LTC. Optional
   * @param startTime start trade time, in ISO 8601 date/time format. Optional
   * @param endTime end trade time, in ISO -8601 date/time format. Optional
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @return an array of {@link BitcointradePublicTradeResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("{currency}/trades")
  BitcointradePublicTradeResponse getTrades(@PathParam("currency") String currency, @QueryParam("start_time") String startTime,
      @QueryParam("end_time") String endTime, @QueryParam("page_size") Integer pageSize, @QueryParam("current_page") Integer currentPage)
      throws BitcointradeException, IOException;
}
