package com.zegameiro.weatherapp;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.zegameiro.weatherapp.ipma_client.IpmaCityForecast;
import com.zegameiro.weatherapp.ipma_client.IpmaService;


/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    private static Logger logger = LogManager.getLogger(WeatherStarter.class);

    public static void main(String[] args) {

        int CITY_ID = 1010500;

        logger.info("Hello World!");

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null)
                logger.info("max temp for today: " + forecast.getData().listIterator().next().getTMax() + "ºC" );
            else 
                logger.error("No results for this request!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}