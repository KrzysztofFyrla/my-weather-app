package pl.krzysztoffyrla.myweatherapp.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krzysztoffyrla.myweatherapp.controller.WeatcherService;

import java.util.ArrayList;

import static com.vaadin.flow.component.notification.Notification.Position.MIDDLE;

/**
 * @author Krzysztof
 * @project my-weather-app
 */
@Route("hello")
@StyleSheet("/css/style.css")
public class MainGui extends VerticalLayout {

    @Autowired
    private WeatcherService weatcherService;

    private HorizontalLayout dashboardLayout;
    private HorizontalLayout dashboardLayout2;
    private Button searchButton;
    private TextField citiesTextField;
    private Label locationLabel;
    private Label tempLabel;
    private Label weatcherDescription;
    private Label minTempLabel;
    private Label maxTempLabel;
    private Label pressureLabel;
    private Label humidityLanel;
    private Label windLabel;
    private Select<String> selectTemperature;
    private Image iconImg;

    public MainGui() {
        iconImg = new Image();
        setHeader();
        setLogo();
        setForm();
        setCietiesTemp();
        setDashboard();

        searchButton.addClickListener(click -> {
           if(!citiesTextField.getValue().equals("")){
               updateUI();
           } else {
               Notification.show("Please Enter The City Name", 2000 , MIDDLE);
           }
        });
    }

    public void setHeader() {
        Label title = new Label("My Weather Application");

        HorizontalLayout headerLabel = new HorizontalLayout(title);
        headerLabel.setJustifyContentMode(JustifyContentMode.CENTER);
        headerLabel.setWidth("100%");
        add(headerLabel);
    }

    public void setLogo() {
        Image img = new Image("/css/logo.png", "weatcher");
        img.setWidth("240px");
        img.setHeight("240px");

        HorizontalLayout headerImg = new HorizontalLayout(img);
        headerImg.setJustifyContentMode(JustifyContentMode.CENTER);
        headerImg.setWidth("100%");
        add(headerImg);
    }

    public void setForm() {
        //Select temperature
        selectTemperature = new Select<>();
        ArrayList<String> items = new ArrayList<>();
        items.add("C");
        items.add("F");
        selectTemperature.setItems(items);
        selectTemperature.setValue(items.get(0));
        selectTemperature.setWidth("6%");

        // Cities Text Field
        citiesTextField = new TextField();
        citiesTextField.setWidth("20%");

        // Search Button
        searchButton = new Button();
        searchButton.setIcon(VaadinIcon.SEARCH.create());

        HorizontalLayout headerSelector = new HorizontalLayout(selectTemperature, citiesTextField, searchButton);
        headerSelector.setJustifyContentMode(JustifyContentMode.CENTER);
        headerSelector.setWidth("100%");
        add(headerSelector);
    }

    public void setCietiesTemp() {
        locationLabel = new Label("Please select a city");
        locationLabel.addClassName("locationLabel");
        tempLabel = new Label("");
        tempLabel.addClassName("tempLabel");

        HorizontalLayout horizontalLayout = new HorizontalLayout(locationLabel, tempLabel);
        HorizontalLayout horizontalLayout2 = new HorizontalLayout(iconImg);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout.setWidth("100%");
        horizontalLayout2.setWidth("100%");
        add(horizontalLayout, horizontalLayout2);
    }

    public void setDashboard() {
        pressureLabel = new Label();
        humidityLanel = new Label();
        windLabel = new Label();

        minTempLabel = new Label();
        maxTempLabel = new Label();

        dashboardLayout = new HorizontalLayout(pressureLabel, humidityLanel, windLabel);
        dashboardLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        dashboardLayout.setWidth("100%");

        dashboardLayout2 = new HorizontalLayout(minTempLabel, maxTempLabel);
        dashboardLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        dashboardLayout2.setWidth("100%");
    }

    public void updateUI() {
        String city = citiesTextField.getValue();
        String defaultUnit;
        weatcherService.setCityName(city);

        if(selectTemperature.getValue().equals("C")) {
            weatcherService.setUnit("metric");
            selectTemperature.setValue("C");
            defaultUnit = "\u00b0" + "C";
        } else {
            weatcherService.setUnit("imperials");
            selectTemperature.setValue("F");
            defaultUnit = "\u00b0" + "F";
        }

        locationLabel.setText("Currently in " + city + ":");
        JSONObject mainObject = weatcherService.returnMain();
        int temp = mainObject.getInt("temp");
        tempLabel.setText(temp + defaultUnit);

        int pressure = mainObject.getInt("pressure");
        pressureLabel.setText("Pressure: " + pressure + " Pa");

        int humidity = mainObject.getInt("humidity");
        humidityLanel.setText("Humidity: " + humidity + "%");

        JSONObject windObject = weatcherService.returnWind();
        int wind = windObject.getInt("speed");
        windLabel.setText("Wind speed: " + wind + " m/s");

        int tempMin = mainObject.getInt("temp_min");
        minTempLabel.setText("Temp min: " + tempMin + defaultUnit);
        int tempMax = mainObject.getInt("temp_max");
        maxTempLabel.setText("Temp max: " + tempMax + defaultUnit);

        // Icon from API
        String iconCode = null;
        String weatcherDescriptionNew = null;
        JSONArray jsonArray = weatcherService.returnWeatherArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject weatherObj = jsonArray.getJSONObject(i);
            iconCode = weatherObj.getString("icon");
            weatcherDescriptionNew = weatherObj.getString("description");
        }

        iconImg.setSrc("http://openweathermap.org/img/wn/" + iconCode + "@2x.png");

        add(dashboardLayout);
        add(dashboardLayout2);
    }
}
