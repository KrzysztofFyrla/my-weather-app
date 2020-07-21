package pl.krzysztoffyrla.myweatherapp.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Krzysztof
 * @project my-weather-app
 */
@Service
public class WeatcherService {

    private OkHttpClient client;
    private Response response;
    private String CityName;
    String unit;
    private String API = "a41d20f8afc03e826e651eed98ae2b3c";

    public JSONObject getWeatcher() {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=" + getCityName() + "&units=" + getUnit()
                        + "&appid=a41d20f8afc03e826e651eed98ae2b3c")
                .build();

        try {
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONArray returnWeatherArray() throws JSONException {
        JSONArray weatherArray = getWeatcher().getJSONArray("weather");
        return weatherArray;
    }

    public JSONObject returnMain() throws JSONException {
        JSONObject main = getWeatcher().getJSONObject("main");
        return main;
    }

    public JSONObject returnWind() throws JSONException {
        JSONObject wind = getWeatcher().getJSONObject("wind");
        return wind;
    }

    public JSONObject returnSys() throws JSONException {
        JSONObject sys = getWeatcher().getJSONObject("sys");
        return sys;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
