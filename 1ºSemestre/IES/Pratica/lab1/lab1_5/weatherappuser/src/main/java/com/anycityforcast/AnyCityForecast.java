package com.anycityforcast;

import com.ipmaapi.CityForecast;
import com.ipmaapi.WeatherStarter;

import java.util.Random;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class AnyCityForecast {
    private static final HashMap<String, Integer> cities = new HashMap<>();
    public void start() {

        cities.put("Aveiro", 1010500);
        cities.put("Beja", 1020500);
        cities.put("Braga", 1030300);
        cities.put("Guimarães", 1030800);
        cities.put("Bragança", 1040200);
        cities.put("Castelo Branco", 1050200);
        cities.put("Coimbra", 1060300);
        cities.put("Évora", 1070500);
        cities.put("Faro", 1080500);
        cities.put("Sagres", 1081505);
        cities.put("Portimão", 1081100);
        cities.put("Loulé", 1080800);
        cities.put("Guarda", 1090700);
        cities.put("Penhas Douradas", 1090821);
        cities.put("Leiria", 1100900);
        cities.put("Lisboa", 1110600);
        cities.put("Portalegre", 1121400);
        cities.put("Porto", 1131200);
        cities.put("Santarém", 1141600);
        cities.put("Setúbal", 1151200);
        cities.put("Sines", 1151300);
        cities.put("Viana do Castelo", 1160900);
        cities.put("Vila Real", 1171400);
        cities.put("Viseu", 1182300);
        cities.put("Funchal", 2310300);
        cities.put("Porto Santo", 2320100);
        cities.put("Vila do Porto", 3410100);
        cities.put("Ponta Delgada", 3420300);
        cities.put("Angra do Heroísmo", 3430100);
        cities.put("Santa Cruz da Graciosa", 3440100);
        cities.put("Velas", 3450200);
        cities.put("Madalena", 3460200);
        cities.put("Horta", 3470100);
        cities.put("Santa Cruz das Flores", 3480200);
        cities.put("Vila do Corvo", 3490100);


        new Timer().scheduleAtFixedRate(new TimerTask() {
            Random rand = new Random();
            @Override
            public void run() {
                int randIndex = rand.nextInt(cities.size());
                String[] citiesArray = cities.keySet().toArray(new String[0]);
                String randCity = citiesArray[randIndex];
                System.out.println("City name: " + randCity);

                int cityCode = cities.get(randCity);
                System.out.println("City code: " + cityCode);

                CityForecast forecast = WeatherStarter.getWeather(cityCode);
                System.out.println(forecast + "\n");
            }
        }, 0, 20 * 1000);
    }

    public static void main(String[] args) {
        new AnyCityForecast().start();
    }
}
