class CityCard {

  final String temperature;
  final String weatherDescription;
  final String cityName;
  final String time;
  final String image;

  CityCard({
    required this.temperature,
    required this.weatherDescription,
    required this.cityName,
    required this.time,
    required this.image
  });

  String getTemperature() {
    return temperature;
  }

  String getWeatherDescription() {
    return weatherDescription;
  }

  String getCityName() {
    return cityName;
  }

  String getTime() {
    return time;
  }

  String getImage() {
    return image;
  }
}

final List<CityCard> cities = [
  CityCard(
    temperature: '26ºC',
    weatherDescription: 'Cloudy',
    cityName: 'Seoul',
    time: 'Time in the city 01h05',
    image: 'assets/images/moon.png'
  ),
  CityCard(
    temperature: '31ºC',
    weatherDescription: 'Overcast',
    cityName: 'New York',
    time: 'Time in the city 12h05',
    image: 'assets/images/cloud.png'
  ),
  CityCard(
    temperature: '26ºC',
    weatherDescription: 'Clear Sky',
    cityName: 'Beijing',
    time: 'Time in the city 00h05',
    image: 'assets/images/full_moon.png'
  ),
];