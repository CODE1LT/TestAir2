# Brief description of functionality

Best practices of clear, readable & extendable code applied. Wheather information is retrieved from openweathermap.org API.

## Application screenshots
<img src="http://www.code1.lt/testair1.png" width="400">  <img src="http://www.code1.lt/testair2.png" width="400">

## Technologies used
MVVM/VIPER/MVI architecture mixture, Livedata, Navigation component, Data binding, Room, Coroutines (+ Flow), OkHTTP, Retrofit, Gson, Airbnb Lottie, Glide, Custom font

## HTTP request syntax
to retrieve city by name:
api.openweathermap.org/data/2.5/weather?q=London,uk

to retrieve cities list by city codes:
api.openweathermap.org/data/2.5/group?id=524901,703448,2643743

## Answer example
```json
{
  "coord": {
    "lon": -0.13,
    "lat": 51.51
  },
  "weather": [
    {
      "id": 803,
      "main": "Clouds",
      "description": "broken clouds",
      "icon": "04n"
    },
    {
      "id": 803,
      "main": "Clouds",
      "description": "mist",
      "icon": "04n"
    },
    {
      "id": 803,
      "main": "Clouds",
      "description": "rain",
      "icon": "04n"
    },
    {
      "id": 803,
      "main": "Clouds",
      "description": "storm",
      "icon": "04n"
    },
    {
      "id": 803,
      "main": "Clouds",
      "description": "thunder",
      "icon": "04n"
    },
    {
      "id": 803,
      "main": "Clouds",
      "description": "light hail",
      "icon": "04n"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 6.99,
    "pressure": 1013,
    "humidity": 87,
    "temp_min": 5.56,
    "temp_max": 8.33
  },
  "visibility": 10000,
  "wind": {
    "speed": 2.1,
    "deg": 250
  },
  "clouds": {
    "all": 75
  },
  "dt": 1575704281,
  "sys": {
    "type": 1,
    "id": 1502,
    "country": "GB",
    "sunrise": 1575705081,
    "sunset": 1575733957
  },
  "timezone": 0,
  "id": 2643743,
  "name": "London",
  "cod": 200
}
```
