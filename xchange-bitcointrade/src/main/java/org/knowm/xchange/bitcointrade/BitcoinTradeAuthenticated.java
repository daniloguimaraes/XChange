package org.knowm.xchange.bitcointrade;

import static org.knowm.xchange.bitcointrade.BitcoinTradeConstants.API_TOKEN_HEADER_NAME;

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
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeDepositListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWithdrawListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcoinTradeWithdrawResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeEstimatedPriceResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcoinTradeSummaryResponse;

/**
 * BitcoinTrade Exchange authenticated end-points.
 *
 * @author Danilo Guimaraes
 */
@Path("v3/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitcoinTradeAuthenticated {

  /**
   * Get the summary (aka ticker)
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param currency the currency (eg. BTC)
   * @return an instance of (@link BitcoinTradeSummaryResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("market/summary")
  BitcoinTradeSummaryResponse getSummary(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken, @QueryParam("currency") String currency)
      throws BitcoinTradeException, IOException;

  /**
   * Create a new order
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param currency the currency
   * @param type the order type (buy or sell)
   * @param amount the amount
   * @param unitPrice the unit price
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @POST
  @Path("market/create_order")
  void createOrder(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken,
      @FormParam("currency") String currency,
      @FormParam("type") String type,
      @FormParam("subtype") String subtype,
      @FormParam("amount") BigDecimal amount,
      @FormParam("unit_price") Integer unitPrice)
      throws BitcoinTradeException, IOException;

  /**
   * Get user orders
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param status the order statys
   * @param startDate start deposit date, in ISO-8601 date format. Optional
   * @param endDate end deposit date, in ISO-8601 date format. Optional
   * @param currency the order currency (eg. BTC)
   * @param type the order type (buy or sell)
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @return an instance of {@link BitcoinTradeUserOrdersResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("market/user_orders")
  BitcoinTradeUserOrdersResponse getUserOrders(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken,
      @QueryParam("status") BitcoinTradeOrderStatus status,
      @QueryParam("start_date") String startDate,
      @QueryParam("end_date") String endDate,
      @QueryParam("currency") String currency,
      @QueryParam("type") String type,
      @QueryParam("page_size") Integer pageSize,
      @QueryParam("current_page") Integer currentPage)
      throws BitcoinTradeException, IOException;

  /**
   * Cancel an order.
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param orderId the order id
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @DELETE
  @Path("market/user_orders")
  String cancelOrder(@HeaderParam(API_TOKEN_HEADER_NAME) String apiToken, @FormParam("id") String orderId)
      throws BitcoinTradeException, IOException;

  /**
   * The estimated price of a cryptocurrency
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param amount the amount
   * @param pair the currency pair (eg. BRLBTC)
   * @param type the type (buy or sell)
   * @return an instance of {@link BitcoinTradeEstimatedPriceResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("market/estimated_price")
  BitcoinTradeEstimatedPriceResponse estimatedPrice(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken,
      @QueryParam("amount") BigDecimal amount,
      @QueryParam("pair") String pair,
      @QueryParam("type") String type)
      throws BitcoinTradeException, IOException;

  /**
   * Withdraw estimated fee
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @return an instance of {@link BitcoinTradeWithdrawListResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("bitcoin/withdraw/fee")
  BitcoinTradeWithdrawListResponse getWithdrawEstimatedFee(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken) throws BitcoinTradeException, IOException;

  /**
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @param orderStatus the order status
   * @param startDate start deposit date, in ISO-8601 date format. Optional
   * @param endDate end deposit date, in ISO-8601 date format. Optional
   * @return an instance of {@link BitcoinTradeWithdrawListResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("bitcoin/withdraw")
  BitcoinTradeWithdrawListResponse getWithdrawList(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken,
      @FormParam("page_size") Integer pageSize,
      @FormParam("current_page") Integer currentPage,
      @FormParam("status") BitcoinTradeOrderStatus orderStatus,
      @FormParam("start_date") String startDate,
      @FormParam("end_date") String endDate)
      throws BitcoinTradeException, IOException;

  /**
   * Create a new withdraw.
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param address the destination address
   * @param fee the fee
   * @param feeType the fee type
   * @param amount the withdraw amount
   * @return an instance of {@link BitcoinTradeWithdrawResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @POST
  @Path("bitcoin/withdraw")
  BitcoinTradeWithdrawResponse withdraw(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken,
      @FormParam("destination") String address,
      @FormParam("fee") BigDecimal fee,
      @FormParam("fee_type") BitcoinTradeFeeType feeType,
      @FormParam("amount") BigDecimal amount)
      throws BitcoinTradeException, IOException;

  /**
   * The list of deposits.
   *
   * @param apiToken the BitcoinTrade Exchange API Token, HTTP Header {@code x-api-key: {apiToken}}
   * @param pageSize the page size. Default: 200. Maximum: 1000. Optional
   * @param currentPage the current page. Default: 1. Optional
   * @param status the deposit status
   * @param startDate start deposit date, in ISO-8601 date format. Optional
   * @param endDate end deposit date, in ISO-8601 date format. Optional
   * @return an instance of {@link BitcoinTradeDepositListResponse}
   * @throws BitcoinTradeException
   * @throws IOException general I/O Exception
   */
  @GET
  @Path("bitcoin/deposits")
  BitcoinTradeDepositListResponse getDepositList(
      @HeaderParam(API_TOKEN_HEADER_NAME) String apiToken,
      @QueryParam("page_size") Integer pageSize,
      @QueryParam("current_page") Integer currentPage,
      @QueryParam("status") BitcoinTradeDepositStatus status,
      @QueryParam("start_date") String startDate,
      @QueryParam("end_date") String endDate)
      throws BitcoinTradeException, IOException;
}
