package pl.krzysztoffyrla.myweatherapp.gui;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

/**
 * @author Krzysztof
 * @project my-weather-app
 */
@Route("hello")
@StyleSheet("/css/style.css")
public class MainGui extends VerticalLayout {

    public MainGui() { //init
        setHeader();
        setLogo();
        setForm();
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
        Select<String> selectTemperature = new Select<>();
        ArrayList<String> items = new ArrayList<>();
        items.add("C");
        items.add("F");
        selectTemperature.setItems(items);
        selectTemperature.setValue(items.get(0));

        HorizontalLayout headerSelector = new HorizontalLayout(selectTemperature);
        headerSelector.setJustifyContentMode(JustifyContentMode.CENTER);
        headerSelector.setWidth("100%");
        add(headerSelector);
    }
}
