package com.example.application.views;

import com.example.application.data.entity.ActorEntry;
import com.example.application.data.service.ActorService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Route(value="dashboard", layout= MainLayout.class)
@PageTitle("Actor Stats | ActorFrontend")
@PermitAll
public class DashboardView extends VerticalLayout {
    private ActorService service;

    public DashboardView(ActorService service){
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats(),getCompaniesChart());
    }

    private Component getCompaniesChart() {
        Span stats=new Span(service.countActors()+" actors");
        stats.addClassNames("text-xl","mt-xl");
        return stats;

    }

    private Component getContactStats() {

        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        List<ActorEntry>actorList=service.findAllActors(null);
        HashMap<String, Long>numActors=new HashMap<>();
        ArrayList<String> actorTypes=new ArrayList<>(List.of("InsultActor", "HelloWorldActor", "RingActor","EncryptionDecorator"));
        actorTypes.forEach(e->
                numActors.put(e,actorList
                        .stream()
                        .filter(a->a.getActorType().equals(e))
                        .count()));

        numActors.forEach((k,v)->{
            dataSeries.add(new DataSeriesItem(k,v));
        });

        chart.getConfiguration().setSeries(dataSeries);
        return chart;

    }


}
