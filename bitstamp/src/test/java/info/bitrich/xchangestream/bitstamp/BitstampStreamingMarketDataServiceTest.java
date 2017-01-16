package info.bitrich.xchangestream.bitstamp;

import info.bitrich.xchangestream.pusher.PusherStreamingService;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BitstampStreamingMarketDataServiceTest {
    @Mock
    private PusherStreamingService streamingService;
    private BitstampStreamingMarketDataService marketDataService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        marketDataService = new BitstampStreamingMarketDataService(streamingService);
    }

    @Test
    public void testGetOrderBook() throws Exception {
        // Given order book in JSON
        String orderBook = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("order-book.json").toURI())));

        when(streamingService.subscribeChannel(any(), anyString())).thenReturn(Observable.just(orderBook));

        List<LimitOrder> bids = new ArrayList<>();
        bids.add(new LimitOrder(Order.OrderType.BID, new BigDecimal("0.922"), CurrencyPair.BTC_USD, "", null, new BigDecimal("819.9")));
        bids.add(new LimitOrder(Order.OrderType.BID, new BigDecimal("0.085"), CurrencyPair.BTC_USD, "", null, new BigDecimal("818.63")));

        List<LimitOrder> asks = new ArrayList<>();
        asks.add(new LimitOrder(Order.OrderType.ASK, new BigDecimal("2.89"), CurrencyPair.BTC_USD, "", null, new BigDecimal("821.7")));
        asks.add(new LimitOrder(Order.OrderType.ASK, new BigDecimal("5.18"), CurrencyPair.BTC_USD, "", null, new BigDecimal("821.65")));
        asks.add(new LimitOrder(Order.OrderType.ASK, new BigDecimal("0.035"), CurrencyPair.BTC_USD, "", null, new BigDecimal("821.6")));

        // Call get order book observable
        TestObserver<OrderBook> test = marketDataService.getOrderBook(CurrencyPair.BTC_USD).test();

        // We get order book object in correct order
        test.assertValue(orderBook1 -> {
            assertThat(orderBook1.getAsks()).isEqualTo(asks);
            assertThat(orderBook1.getBids()).isEqualTo(bids);
            return true;
        });
    }

}