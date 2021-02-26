import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class Weather {
    private static final String API_KEY="d5ddc3bdb813a23fe8fa89edbac1cca7";
    private static final String URL_CURRENT = "http://api.openweathermap.org/data/2.5/weather?" +
            "lang=ru&" +
            "units=metric&" +
            "q=%s&" +
            "appid=%s";
    public static String getWeather(String city) throws IOException {
        // forming a query line (I'll set the city and the token)
        String requesUrl = String.format(URL_CURRENT, city, API_KEY);

        URL url = new URL(requesUrl);
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

        int temp;
        if (mainMap.get("temp") instanceof BigDecimal) {
            temp = ((BigDecimal) mainMap.get("temp")).intValue();
        } else {
            temp = (Integer) mainMap.get("temp");
        }

        city = (String) jsonMap.get("name");
        result = String.format("Текущая температура в городе %s: %d", city, temp);

        return result;
    }

}
