import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Weather {
    private static final String API_KEY="d5ddc3bdb813a23fe8fa89edbac1cca7";
    private static final String URL_CURRENT = "http://api.openweathermap.org/data/2.5/weather?" +
            "lang=ru&" +
            "units=metric&" +
            "q=%s&" +
            "appid=%s";
    public static String getWeather(String city) throws IOException {
        String requestUrl = String.format(URL_CURRENT,city,API_KEY);
        URL url = new URL(requestUrl);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer buffer = new StringBuffer();
        bufferedReader.lines().forEach(line->buffer.append(line));



        System.out.println("Connection done!");
        System.out.println(buffer.toString());
        return buffer.toString();
    }

}
