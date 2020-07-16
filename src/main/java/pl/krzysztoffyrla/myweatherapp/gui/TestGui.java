package pl.krzysztoffyrla.myweatherapp.gui;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

/**
 * @author Krzysztof
 * @project my-weather-app
 */
@Route("test")
public class TestGui extends VerticalLayout {

    public TestGui() {

        // HEADER
        Icon drawer = VaadinIcon.MENU.create();
        Span title = new Span("My application");
        Icon help = VaadinIcon.QUESTION_CIRCLE.create();
        HorizontalLayout header = new HorizontalLayout(drawer, title, help);
        header.expand(title);
        header.setPadding(true);
        header.setWidth("100%");

        // WORKSPACE
        VerticalLayout workspace = new VerticalLayout();
        workspace.setSizeFull();

        // FOOTER
        Tab actionButton1 = new Tab(VaadinIcon.HOME.create(), new Span("Home"));
        Tab actionButton2 = new Tab(VaadinIcon.USERS.create(), new Span("Customers"));
        Tab actionButton3 = new Tab(VaadinIcon.PACKAGE.create(), new Span("Products"));
        Tabs buttonBar = new Tabs(actionButton1, actionButton2, actionButton3);
        HorizontalLayout footer = new HorizontalLayout(buttonBar);
        footer.setJustifyContentMode(JustifyContentMode.CENTER);
        footer.setWidth("100%");

        // MAIN CONTAINER
        setSizeFull();
        setMargin(false);
        setSpacing(false);
        setPadding(false);
        add(header, workspace, footer);
    }
}
