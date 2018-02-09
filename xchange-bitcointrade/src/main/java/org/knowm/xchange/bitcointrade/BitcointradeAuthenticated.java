package org.knowm.xchange.bitcointrade;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bitcointrade.dto.account.BitcointradeDepositListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeWithdrawListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeWithdrawResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeEstimatedPriceResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeSummaryResponse;

/**
 * Bitcointrade Exchange authenticated end-points.
 *
 * @author Danilo Guimaraes
 */
@Path("v1/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitcointradeAuthenticated {

  /**
   * Get the summary (aka ticker)
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param currency the currency (eg. BTC)
   * @return an instance of (@link BitcointradeSummaryResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("market/summary")
  BitcointradeSummaryResponse getSummary(@HeaderParam("Authorization") String apiToken, @QueryParam("currency") String currency)
      throws BitcointradeException, IOException;

  /**
   * Create a new order
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param currency the currency
   * @param type the order type (buy or sell)
   * @param amount the amount
   * @param unitPrice the unit price
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @POST
  @Path("market/create_order")
  void createOrder(@HeaderParam("Authorization") String apiToken, @FormParam("currency") String currency, @FormParam("type") String type,
      @FormParam("subtype") String subtype, @FormParam("amount") BigDecimal amount, @FormParam("unit_price") Integer unitPrice)
      throws BitcointradeException, IOException;

  /**
   * Get user orders
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param status the order statys
   * @param startDate start deposit date, in ISO-8601 date format. Optional
   * @param endDate end deposit date, in ISO-8601 date format. Optional
   * @param currency the order currency (eg. BTC)
   * @param type the order type (buy or sell)
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @return an instance of {@link BitcointradeUserOrdersResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("market/user_orders")
  BitcointradeUserOrdersResponse getUserOrders(@HeaderParam("Authorization") String apiToken, @QueryParam("status") BitcointradeOrderStatus status,
      @QueryParam("start_date") String startDate, @QueryParam("end_date") String endDate, @QueryParam("currency") String currency,
      @QueryParam ("type") String type, @QueryParam("page_size") Integer pageSize, @QueryParam("current_page") Integer currentPage)
      throws BitcointradeException, IOException;

  /**
   * Cancel an order.
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param orderId the order id
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @DELETE
  @Path("market/user_orders")
  String cancelOrder(@HeaderParam("Authorization") String apiToken, @FormParam("id") String orderId) throws BitcointradeException, IOException;

  /**
   * The estimated price of a cryptocurrency
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param amount the amount
   * @param currency the currency (eg. BTC)
   * @param type the type  (buy or sell)
   * @return an instance of {@link BitcointradeEstimatedPriceResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("market/estimated_price")
  BitcointradeEstimatedPriceResponse estimatedPrice(@HeaderParam("Authorization") String apiToken, @QueryParam("amount") BigDecimal amount,
      @QueryParam("currency") String currency, @QueryParam("type") String type) throws BitcointradeException, IOException;

  /**
   * Withdraw estimated fee
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @return an instance of {@link BitcointradeWithdrawListResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("bitcoin/withdraw/fee")
  BitcointradeWithdrawListResponse getWithdrawEstimatedFee(@HeaderParam("Authorization") String apiToken) throws BitcointradeException, IOException;

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @param orderStatus the order status
   * @param startDate start deposit date, in ISO-8601 date format. Optional
   * @param endDate end deposit date, in ISO-8601 date format. Optional
   * @return an instance of {@link BitcointradeWithdrawListResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("bitcoin/withdraw")
  BitcointradeWithdrawListResponse getWithdrawList(@HeaderParam("Authorization") String apiToken, @FormParam("page_size") Integer pageSize,
      @FormParam("current_page") Integer currentPage, @FormParam("status") BitcointradeOrderStatus orderStatus,
      @FormParam("start_date") String startDate, @FormParam("end_date") String endDate) throws BitcointradeException, IOException;

  /**
   * Create a new withdraw.
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param address the destination address
   * @param fee the fee
   * @param feeType the fee type
   * @param amount the withdraw amount
   * @return an instance of {@link BitcointradeWithdrawResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @POST
  @Path("bitcoin/withdraw")
  BitcointradeWithdrawResponse withdraw(@HeaderParam("Authorization") String apiToken, @FormParam("destination") String address,
      @FormParam("fee") BigDecimal fee, @FormParam("fee_type") BitcointradeFeeType feeType, @FormParam("amount") BigDecimal amount)
      throws BitcointradeException, IOException;

  /**
   * The list of deposits.
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @param status the deposit status
   * @param startDate start deposit date, in ISO-8601 date format. Optional
   * @param endDate end deposit date, in ISO-8601 date format. Optional
   * @return an instance of {@link BitcointradeDepositListResponse}
   * @throws BitcointradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("bitcoin/deposits")
  BitcointradeDepositListResponse getDepositList(@HeaderParam("Authorization") String apiToken, @QueryParam("page_size") Integer pageSize,
      @QueryParam("current_page") Integer currentPage, @QueryParam("status") BitcointradeDepositStatus status,
      @QueryParam("start_date") String startDate, @QueryParam("end_date") String endDate) throws BitcointradeException, IOException;
}
