import 'package:flutter/material.dart';

import 'package:weatherapp/cards/citycard.dart';
import 'package:weatherapp/utils/utils.dart';

class CityPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(15.0),
      child: Column(
        children: [
          SizedBox(
            height: 400,
            child: ListView.builder(
              itemCount: cities.length,
              itemBuilder: (context, index) {
                final cityCard = cities[index];
                return buildCityCard(
                  temperature: cityCard.temperature,
                  weatherDescription: cityCard.weatherDescription,
                  cityName: cityCard.cityName,
                  time: cityCard.time,
                  image: cityCard.image
                );
              },
            ),
          ),
          Align(
            alignment: Alignment.centerRight,
            child: FloatingActionButton(
              onPressed: (){},
              backgroundColor: Colors.deepPurple,
              child: Icon(Icons.add),
            ),
          )
        ],
      ),
    );
  }
}