package deti.tqs.backend.services;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {
  
  private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

  private Set<String> currencies;
  public Map<String, Object> cachedRates = new HashMap<>();
  private int cachedTtl = 3600 * 1000;
  private long lastCaching = 0;
  private String apiKey = "a0c7aedc93a1123e9d9b2c72";

  @Autowired
  public CurrencyExchangeService() {}

  public CurrencyExchangeService(int ttl) {
    cachedTtl = ttl;
    cachedRates = new HashMap<>();
  }

  public boolean cacheExchangeRates(Map<String, Object> rates) {
    cachedRates = rates;
    lastCaching = System.currentTimeMillis();
    logger.info("Rates cached successfully at {0} with ttl {1}", lastCaching, cachedTtl);
    return true;
  }

  public boolean checkCache() {

    if (cachedRates.isEmpty())
      return false;

    if (lastCaching == 0)
      return false;

    if (System.currentTimeMillis() < lastCaching + cachedTtl)
      return true;

    else
      return false;
  }

  public Set<String> listCurrencies() throws Exception {
    logger.info("Listing currencies");

    if (currencies == null) 
      exchange("EUR", "USD");

    return currencies;
  }

  public double exchange(String from, String to) throws Exception {

    if(checkCache()) {
      double rate = Double.parseDouble(cachedRates.get(to).toString());
      return rate;
    } else 
      logger.info("Cache is not valid, making another exchange rates request");

    String api_link = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

    String content = requestExchanges(api_link);

    JSONObject obj = new JSONObject(content.toString());

    cacheExchangeRates(obj.getJSONObject("conversion_rates").toMap());

    currencies = obj.getJSONObject("conversion_rates").keySet();

    double rate = 0.0;

    try {
      rate = obj.getJSONObject("conversion_rates").getDouble(to);
    } catch (Exception e) {
      throw new Exception("Currency not found");
    }

    return rate;
  }
  

  public String requestExchanges(String link) throws Exception {

    URL url = new URL(link);

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    connection.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    String inputLine;

    StringBuilder content = new StringBuilder();

    while ((inputLine = in.readLine()) != null)
      content.append(inputLine);

    in.close();
    connection.disconnect();

    return content.toString();
  }

}
