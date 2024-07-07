
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import tqs.IStockmarketService;
import tqs.Stock;
import tqs.StocksPortofolio;

@ExtendWith(MockitoExtension.class)
public class StockPortofolioTest {

    @InjectMocks
    StocksPortofolio portofolio;

    @Mock
    IStockmarketService stockmarketService;

    @Test
    public void testTotalValue(){

        // Load the mock with expectations
        when(stockmarketService.lookUpPrice("Tesla")).thenReturn(2.6);
        when(stockmarketService.lookUpPrice("Nikolai")).thenReturn(5.9);
        when(stockmarketService.lookUpPrice("Galp")).thenReturn(1.6);

        portofolio.addStock(new Stock("Tesla", 1));
        portofolio.addStock(new Stock("Nikolai", 5));
        portofolio.addStock(new Stock("Galp", 9));

        // Assertions.assertEquals(46.5, portofolio.totalValue());

        assertThat(portofolio.totalValue(), equalTo(46.5));

        verify(stockmarketService, times(3)).lookUpPrice(anyString());
    }

}
