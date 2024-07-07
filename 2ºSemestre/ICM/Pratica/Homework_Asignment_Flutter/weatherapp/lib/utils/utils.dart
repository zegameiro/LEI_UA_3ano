import "package:flutter/material.dart";

// Home Page Functions
Widget buildHourlyCard({
  required String time, 
  required String day, 
  required String image, 
  required String temperature, 
  required bool current,
  required bool hasDivider}) 
{
  return Row(
    children: [
      Card(
        color: current ? Colors.deepPurple : null,
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Center(child: Text(time)),
              Center(
                child: Text(
                  day,
                  style: TextStyle(
                    fontSize: 14,
                    color: Colors.grey
                  ),
                )
              ),
              Center(
                child: Image.asset(
                  image,
                  width: 40,
                  height: 40,
                ),
              ),
              Center(
                child: Text(
                  temperature,
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w600,
                  ),
                ),
              )
            ],
          ),
        ),
      ),
      hasDivider ?
        VerticalDivider(
          thickness: 1,
          color: Colors.grey,
          indent: 40,
          endIndent: 40,
        )
      :
        Icon(null)
    ],
  );
}

Widget buildDailyCard({
  required String day,
  required String image,
  required String description,
  required String temperature
}) {
  return Container(
    margin: EdgeInsets.symmetric(vertical: 8),
    child: Row(
      children: [
        Expanded(
          flex: 2,
          child: Align(
            alignment: Alignment.centerRight,
            child: Text(
              day,
              style: TextStyle(
                fontSize: 12
              ),
            ),
          )
        ),
        Expanded(
          flex: 6,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(
                image,
                width: 40,
                height: 40,
              ),
              Text(description),
            ],
          ),
        ),
        Expanded(
          flex: 2,
          child: Align(
            alignment: Alignment.centerRight,
            child: Text(
              temperature,
              style: TextStyle(
                fontSize: 12
              ),
            ),
          ),
        )
      ],
    ),
  );
}

// City Page Function
Widget buildCityCard({
  required String temperature,
  required String weatherDescription,
  required String cityName,
  required String time,
  required String image
}) {
  return Card(
    child: Row(
      children: [
        Expanded(
          flex: 4,
          child: Align(
            alignment: Alignment.centerLeft,
            child: Padding(
              padding: EdgeInsets.all(15),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        temperature,
                        style: TextStyle(
                          fontSize: 25,
                          fontWeight: FontWeight.bold
                        ),
                      ),
                      Padding(
                        padding: EdgeInsets.only(left: 15, top: 7),
                        child: Text(
                          weatherDescription,
                          style: TextStyle(
                            fontSize: 16,
                            color: Colors.grey,
                          ),
                        ),
                      )
                    ],
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 8),
                    child: Text(
                      cityName,
                      style: TextStyle(
                        fontSize: 16,
                      ),
                    ),
                  ),
                  Text(
                    time,
                    style: TextStyle(
                      fontSize: 16,
                      color: Colors.grey
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
        Expanded(
          flex: 4,
          child: Align(
            alignment: Alignment.centerRight,
            child: Padding(
              padding: EdgeInsets.all(15),
              child: Image.asset(
                image,
                width: 90,
                height: 90,
              ),
            ),
          ),
        ),
      ],
    ),
  );
}

// Settings Page function
Widget buildSettingsCard({required String label, required IconData icon, required String optionalLabel, required bool hasChevron}) {
  return Card(
    child: Padding(
      padding: const EdgeInsets.all(8.0),
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10),
        child: Row(
          children: [
            Expanded(
              flex: 4,
              child: Align(
                alignment: Alignment.centerLeft,
                child: Row(
                  children: [
                    Icon(icon),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 10),
                      child: Text(label),
                    )
                  ],
                ),
              )
            ),
            optionalLabel != "" ?
              Align(
                alignment: Alignment.centerRight,
                child: Row(
                  children: [
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 4),
                      child: Text(
                        optionalLabel,
                        style: TextStyle(
                          fontSize: 12,
                        ),
                      ),
                    ),
                    hasChevron ? Icon(Icons.chevron_right) : Icon(null),
                  ],
                )
              )
            :
            Expanded(
              flex: 4,
              child: Align(
                alignment: Alignment.centerRight,
                child: hasChevron ? Icon(Icons.chevron_right) : Icon(null),
              )
            )
          ],
        ),
      ),
    ),
  );
}