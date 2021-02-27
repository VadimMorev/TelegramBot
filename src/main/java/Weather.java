import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Weather {
    private static final String API_KEY = "d5ddc3bdb813a23fe8fa89edbac1cca7";
    private static final String URL_CURRENT = "http://api.openweathermap.org/data/2.5/weather?" +
            "lang=ru&" +
            "units=metric&" +
            "q=%s&" +
            "appid=%s";

    public static String getWeather(String city) throws IOException {
        // forming a query line (I'll set the city and the token)
        String requestUrl = String.format(URL_CURRENT, city, API_KEY);

        URL url = new URL(requestUrl);
        // I'm doing a request and I'm getting a response
        URLConnection conn = url.openConnection();
        System.out.println("Connection done!");

        // extract data from the answer
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        StringBuffer buffer = new StringBuffer();
        // response lines in the text buffer
        reader.lines().forEach(line -> buffer.append(line));
        System.out.println(buffer);

        String result = buffer.toString();
        // deserialization from JSON-string to object
        JSONObject json = new JSONObject(result);

        Map<String, Object> jsonMap = json.toMap();

        Map<String, Object> mainMap = (Map<String, Object>) jsonMap.get("main");
        String tempString = "";
        int temp;

        if (mainMap.get("temp") instanceof Double) {
            temp = ((Double) mainMap.get("temp")).intValue();
        } else {
            temp = (Integer) mainMap.get("temp");
        }
        if (temp > 0) {
            tempString = "+" + temp;
        } else {
            tempString = temp + "";
        }
        Map<String,Object> windMap= (Map<String, Object>) jsonMap.get("wind");
        double windSpeed;
        if(windMap.get("speed")instanceof Integer){
            windSpeed=((Integer) windMap.get("speed")).doubleValue();
        }else {
            windSpeed = (Double) windMap.get("speed");
        }
        ArrayList<Object> arrayList= (ArrayList<Object>) jsonMap.get("weather");


        Map<String,Object>weatherMap= (Map<String, Object>) arrayList.get(0);
        String weatherDescription = weatherMap.get("description").toString();
        weatherDescription=Character.toUpperCase(weatherDescription.charAt(0))+weatherDescription.substring(1);

        city = (String) jsonMap.get("name");
        result = String.format("Текущая температура в городе %s: %s °С. %s. Скорость ветра: %.2f м/с", city, tempString, weatherDescription,windSpeed);
        System.out.println(result);
        return result;
    }

}
