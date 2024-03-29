package com.example.application.views;


import com.example.application.views.list.ListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {


    public MainLayout(){

        createHeader();
        createDrawer();
    }

    /**
     * Crea el encabezado de la aplicación
     */
    private void createHeader() {
        H1 logo=new H1("ACTOR FRONTEND");
        logo.addClassNames("text-l","m-m");
        //Button logOut = new Button("log out", e -> securityService.logOut());
        Image image = new Image("./icons/github-logo.png", "GitHub Logo");

        image.addClassName("gitHubImage");
        image.setWidth(45, Unit.PIXELS);
        image.setHeight(45, Unit.PIXELS);
        image.addClickListener(e->{
            UI.getCurrent().getPage().open("https://github.com/carlosmgv02/TAPPrac1", "_blank");
        });

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo,image);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0","px-m");
        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listView = new RouterLink("List", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation()); //Para que se resalte el link cuando estemos en esa vista

        addToDrawer(new VerticalLayout(
                listView,
                new RouterLink("Dashboard", DashboardView.class),
                new RouterLink("Ring Actor", RingActorLayout.class)
        ));

    }

}
