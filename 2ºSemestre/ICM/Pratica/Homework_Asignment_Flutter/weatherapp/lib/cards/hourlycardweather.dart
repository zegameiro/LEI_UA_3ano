class HourlyCardWeather {

  final String time;
  final String day;
  final String image;
  final String temperature;
  final bool iscurrent;
  final bool hasDivider;

  HourlyCardWeather({
    required this.time,
    required this.day,
    required this.image,
    required this.temperature,
    required this.iscurrent,
    required this.hasDivider
  });

  String getTime() {
    return time;
  }

  String getDay() {
    return day;
  }

  String getImage() {
    return image;
  }

  String getTemperature() {
    return temperature;
  }

  bool getIsCurrent() {
    return iscurrent;
  }

  bool getHasDivider() {
    return hasDivider;
  }
}

final List<HourlyCardWeather> weatherHours = [
  HourlyCardWeather(
    time: '19h00', 
    day: 'Mon', 
    image: 'assets/images/clear_day.png', 
    temperature: '20ºC', 
    iscurrent: true,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '20h00', 
    day: 'Mon', 
    image: 'assets/images/cloudy_day.png', 
    temperature: '19ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '21h00', 
    day: 'Mon', 
    image: 'assets/images/cloudy_night.png', 
    temperature: '18ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '22h00', 
    day: 'Mon', 
    image: 'assets/images/cloudy_night.png', 
    temperature: '18ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '23h00', 
    day: 'Mon', 
    image: 'assets/images/clear_night.png', 
    temperature: '17ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '00h00', 
    day: 'Tue', 
    image: 'assets/images/clear_night.png', 
    temperature: '17ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '01h00', 
    day: 'Tue', 
    image: 'assets/images/fog_night.png', 
    temperature: '15ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '02h00', 
    day: 'Tue', 
    image: 'assets/images/thunder_night.png', 
    temperature: '13ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '03h00', 
    day: 'Tue', 
    image: 'assets/images/fog_night.png', 
    temperature: '14ºC', 
    iscurrent: false,
    hasDivider: true
  ),
  HourlyCardWeather(
    time: '04h00', 
    day: 'Tue', 
    image: 'assets/images/fog_night.png', 
    temperature: '15ºC', 
    iscurrent: false,
    hasDivider: false
  ),
];