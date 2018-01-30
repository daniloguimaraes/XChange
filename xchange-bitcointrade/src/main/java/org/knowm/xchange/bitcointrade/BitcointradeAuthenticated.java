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

import org.knowm.xchange.bitcointrade.dto.BitcointradeBaseResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeDepositListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeUserOrdersResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeWithdrawListResponse;
import org.knowm.xchange.bitcointrade.dto.account.BitcointradeWithdrawResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeEstimatedPriceResponse;
import org.knowm.xchange.bitcointrade.dto.marketdata.BitcointradeSummaryResponse;

/**
 * @author Danilo Guimaraes
 */
@Path("v1/market/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitcointradeAuthenticated {

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param currency
   * @return
   * @throws BitcointradeException
   * @throws IOException
   */
  @GET
  @Path("summary")
  BitcointradeSummaryResponse getSummary(@HeaderParam("Authorization") String apiToken, @QueryParam("currency") String currency)
      throws BitcointradeException, IOException;

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param currency
   * @param type
   * @param amount
   * @param unitPrice
   */
  @POST
  @Path("create_order")
  void createOrder(@HeaderParam("Authorization") String apiToken, @FormParam("currency") String currency, @FormParam("type") String type,
      @FormParam("subtype") String subtype, @FormParam("amount") BigDecimal amount, @FormParam("unit_price") Integer unitPrice)
      throws BitcointradeException, IOException;

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param status
   * @param startDate
   * @param endDate
   * @param currency
   * @param type
   * @param pageSize
   * @param currentPage
   * @return
   * @throws BitcointradeException
   * @throws IOException
   */
  @GET
  @Path("user_orders")
  BitcointradeUserOrdersResponse getUserOders(@HeaderParam("Authorization") String apiToken, @QueryParam("status") BitcointradeOrderStatus status,
      @QueryParam("start_date") String startDate, @QueryParam("end_date") String endDate, @QueryParam("currency") String currency,
      @QueryParam ("type") String type, @QueryParam("page_size") Integer pageSize, @QueryParam("current_page") Integer currentPage)
      throws BitcointradeException, IOException;

  /**
   * Cancel an order.
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param orderId the order id
   * @throws BitcointradeException
   * @throws IOException
   */
  @DELETE
  @Path("cancel_order")
  String cancelOrder(@HeaderParam("Authorization") String apiToken, @FormParam("id") String orderId) throws BitcointradeException, IOException;

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param amount
   * @param currency
   * @param type
   * @return
   * @throws BitcointradeException
   * @throws IOException
   */
  @GET
  @Path("estimated_price")
  BitcointradeEstimatedPriceResponse estimatedPrice(@HeaderParam("Authorization") String apiToken, @QueryParam("amount") BigDecimal amount,
      @QueryParam("currency") String currency, @QueryParam("type") String type)

      throws BitcointradeException, IOException;

  /**
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @return
   */
  @GET
  @Path("withdraw/fee")
  BitcointradeWithdrawListResponse getWithdrawEstimatedFee(@HeaderParam("Authorization") String apiToken) throws BitcointradeException, IOException;

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param pageSize
   * @param currentPage
   * @param orderStatus
   * @param startDate
   * @param endDate
   * @return
   * @throws BitcointradeException
   * @throws IOException
   */
  @GET
  @Path("withdraw")
  BitcointradeWithdrawListResponse getWithdrawList(@HeaderParam("Authorization") String apiToken, @FormParam("page_size") Integer pageSize,
      @FormParam("current_page") Integer currentPage, @FormParam("status") BitcointradeOrderStatus orderStatus,
      @FormParam("start_date") String startDate, @FormParam("end_date") String endDate) throws BitcointradeException, IOException;

  /**
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param address
   * @param fee
   * @param feeType
   * @param amount
   * @return
   * @throws BitcointradeException
   * @throws IOException
   */
  @POST
  @Path("withdraw")
  BitcointradeWithdrawResponse withdraw(@HeaderParam("Authorization") String apiToken, @FormParam("destination") String address,
      @FormParam("fee") BigDecimal fee, @FormParam("fee_type") BitcointradeFeeType feeType, @FormParam("amount") BigDecimal amount)
      throws BitcointradeException, IOException;

  /**
   *
   * @param apiToken the Bitcointrade Exchange API Token, HTTP Header {@code Authorization: ApiToken {apiToken}}
   * @param address
   * @param fee
   * @param feeType
   * @param amount
   * @return
   * @throws BitcointradeException
   * @throws IOException
   */
  @GET
  @Path("deposits")
  BitcointradeDepositListResponse getDepositList(@HeaderParam("Authorization") String apiToken, @FormParam("destination") String address,
      @FormParam("fee") BigDecimal fee, @FormParam("fee_type") BitcointradeFeeType feeType, @FormParam("amount") BigDecimal amount)
      throws BitcointradeException, IOException;
}
