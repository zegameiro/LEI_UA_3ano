package tqs;

import java.util.List;
import java.util.ArrayList;

public class StocksPortofolio {

    private IStockmarketService stockmarketService;
    private List<Stock> stocks;

    public StocksPortofolio(IStockmarketService sms) {
        this.stockmarketService = sms;
        this.stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock s) {
        this.stocks.add(s);
    }

    public double totalValue() {
        double total = 0.0;

        for(Stock s : stocks) 
            total += stockmarketService.lookUpPrice(s.getLabel()) * s.getQuantity();

        return total;
    }
}
