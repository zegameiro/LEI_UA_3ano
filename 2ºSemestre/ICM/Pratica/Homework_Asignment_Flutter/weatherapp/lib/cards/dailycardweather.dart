class DailyCardWeather{
  final String day;
  final String image;
  final String description;
  final String temperature;

  DailyCardWeather({
    required this.day,
    required this.image,
    required this.description,
    required this.temperature
  });

  String getDay(){
    return day;
  }

  String getImage() {
    return image;
  }

  String getDescription() {
    return description;
  }

  String getTemperature() {
    return temperature;
  }
}

final List<DailyCardWeather> dailyCards= [
  DailyCardWeather(
    day: 'Monday', 
    image: 'assets/images/cloudy_day.png', 
    description: 'Overcast',
    temperature: '13ºC/22ºC'
  ),
  DailyCardWeather(
    day: 'Tuesday', 
    image: 'assets/images/cloudy_day.png', 
    description: 'Overcast',
    temperature: '14ºC/21ºC'
  ),
  DailyCardWeather(
    day: 'Wednesday', 
    image: 'assets/images/thunder_day.png', 
    description: 'Thunder...',
    temperature: '12ºC/19ºC'
  ),
  DailyCardWeather(
    day: 'Thursday', 
    image: 'assets/images/cloudy_day.png', 
    description: 'Overcast',
    temperature: '12ºC/18ºC'
  ),
  DailyCardWeather(
    day: 'Saturday', 
    image: 'assets/images/cloudy_day.png', 
    description: 'Overcast',
    temperature: '11ºC/20ºC'
  ),
  DailyCardWeather(
    day: 'Sunday', 
    image: 'assets/images/cloudy_day.png', 
    description: 'Overcast',
    temperature: '10ºC/20ºC'
  ),
];


