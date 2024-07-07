package deti.tqs.backend.unit_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import deti.tqs.backend.services.CurrencyExchangeService;

public class TestCache {
  
  private static CurrencyExchangeService currencyService = new CurrencyExchangeService(6000);

  @Test
  @DisplayName("Check when time to live expires the cache is invalid, otherwise the cache must be valid")
  void testCacheAfterTimetoLiveExpires() throws Exception {
    currencyService.exchange("EUR", "USD");
    assertThat(currencyService.checkCache()).isTrue();

    Thread.sleep(7000);
    assertThat(currencyService.checkCache()).isFalse();
  }

  @Test
  @DisplayName("Check if the list of currencies has the right values EUR and USD")
  void testListCurrencies() throws Exception {
    assertThat(currencyService.listCurrencies()).contains("EUR", "USD");
  }

}
