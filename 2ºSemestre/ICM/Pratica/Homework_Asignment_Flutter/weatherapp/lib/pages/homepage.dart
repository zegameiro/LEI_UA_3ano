import 'package:flutter/material.dart';
import 'package:weatherapp/cards/hourlycardweather.dart';
import 'package:weatherapp/cards/dailycardweather.dart';
import 'package:weatherapp/utils/utils.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: Column(
        children: [
          Center(
            child: Image.asset(
              "assets/images/cloud.png",
              width: 200,
              height: 200,
            ),
          ),
          Center(
            child: Text(
              "20ºC",
              style: TextStyle(
                fontSize: 90.0,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Center(
            child: Text(
              "Cloudy",
              style: TextStyle(
                fontSize: 20.0,
              ),
            ),
          ),
          Center(
            child: Text(
              "Monday, August 21",
              style: TextStyle(
                color: Colors.grey,
              ),
            ),
          ),
          Card(
            margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 8),
            child: Padding(
              padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 20),
              child: SizedBox(
                height: 155,
                child: ListView.builder(
                  itemCount: weatherHours.length,
                  scrollDirection: Axis.horizontal,
                  itemBuilder: (context, index) {
                    final HourlyCardWeather card = weatherHours[index];
                    return buildHourlyCard(
                      time: card.time, 
                      day: card.day, 
                      image: card.image, 
                      temperature: card.temperature,
                      current: card.iscurrent,
                      hasDivider: card.hasDivider,
                    );
                  },
                ),
              ),
            ),
          ),
          Card(
            child: Padding(
              padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 20),
              child: Column(
                children: [
                  Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Align(
                          alignment: Alignment.centerLeft,
                          child: Column(
                            children: [
                              Text(
                                'Sunrise',
                                style: TextStyle(
                                  fontSize: 15,
                                ),
                              ),
                              Text(
                                '05h12',
                                style: TextStyle(
                                  fontSize: 20,
                                ),
                              )
                            ],
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Align(
                          alignment:Alignment.centerLeft,
                          child: Image.asset(
                            "assets/images/sunrise.png",
                            width: 75,
                            height: 75,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 4,
                        child: Align(
                          alignment: Alignment.centerRight,
                          child: Column(
                            children: [
                              Text(
                                'Sunset',
                                style: TextStyle(
                                  fontSize: 15,
                                ),
                              ),
                              Text(
                                '19h52',
                                style: TextStyle(
                                  fontSize: 20,
                                ),
                              )
                            ],
                          ),
                        ),
                      ),
                      Align(
                        alignment: Alignment.centerRight,
                        child: Image.asset(
                          "assets/images/sunset.png",
                          width: 75,
                          height: 75,
                        ),
                      ),
                    ],
                  ),
                  Padding(
                    padding: EdgeInsets.symmetric(vertical: 10),
                    child: Divider(
                      thickness: 1,
                      color: Colors.grey,
                      indent: 15,
                      endIndent: 15,
                    ),
                  ),
                  Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Align(
                          alignment: Alignment.centerLeft,
                          child: Column(
                            children: [
                              Image.asset(
                                'assets/images/cloudy.png',
                                height: 70,
                                width: 70,
                              ),
                              Center(
                                child: Text(
                                  '47%',
                                ),
                              ),
                              Text(
                                'Cloudcover',
                                style: TextStyle(
                                  fontSize: 12
                                ),
                              )
                            ],
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Align(
                          alignment: Alignment.center,
                          child: Column(
                            children: [
                              Image.asset(
                                'assets/images/atmospheric.png',
                                height: 70,
                                width: 70,
                              ),
                              Center(
                                child: Text(
                                  '999hPa',
                                ),
                              ),
                              Text(
                                'Pressure',
                                style: TextStyle(
                                  fontSize: 12
                                ),
                              )
                            ],
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Align(
                          alignment: Alignment.centerRight,
                          child: Column(
                            children: [
                              Image.asset(
                                'assets/images/sun.png',
                                height: 70,
                                width: 70,
                              ),
                              Center(
                                child: Text(
                                  '1',
                                ),
                              ),
                              Text(
                                'UV-index',
                                style: TextStyle(
                                  fontSize: 12
                                ),
                              )
                            ],
                          ),
                        ),
                      )
                    ],
                  )
                ],
              )
            ),
          ),
          Card(
            child: Padding(
              padding: const EdgeInsets.only(right: 10, left: 1),
              child: Column(
                children: [
                  SizedBox(
                    height: 350,
                    child: ListView.builder(
                      itemCount: dailyCards.length,
                      itemBuilder: (context, index) {
                        final daily = dailyCards[index];
                        return buildDailyCard(
                          day: daily.day, 
                          image: daily.image, 
                          description: daily.description,
                          temperature: daily.temperature
                        );
                      },
                    ),
                  ),
                  Divider(
                    color: Colors.grey,
                    thickness: 1,
                    indent: 30,
                    endIndent: 30,
                  ),
                  Center(
                    child: Padding(
                      padding: const EdgeInsets.only(bottom: 20, top: 10),
                      child: Text(
                        '12-day Weather Forecast',
                        style: TextStyle(
                          fontSize: 17
                        ),
                      ),
                    ),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}