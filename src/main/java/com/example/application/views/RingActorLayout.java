package com.example.application.views;

import ActorModel.ActorProxy;
import ActorModel.Messages.Message;
import ActorModel.RingActor;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.concurrent.ExecutorService;

@PageTitle("Ring Actor | Actor Frontend")
@Route(value = "ring", layout = MainLayout.class)
@PermitAll
public class RingActorLayout extends VerticalLayout {
    Span status = new Span("Pending");
    MessageInput messageInput = new MessageInput();
    IntegerField nActors;
    Button submit = new Button("Submit");

    public RingActorLayout() {
        status.getElement().getThemeList().add("badge pill");
        addClassName("ring-view");
        setSizeFull();
        nActors = new IntegerField();
        submit.addClickListener(e -> {
            status.setText("Running");
            //status.getElement().getThemeList().add("badge contrast");


            createRing(nActors.getValue());
            status.setText("Finished");
            status.getElement().getThemeList().add("badge error pill");

        });
        add(updateScreen());


    }

    public Component updateScreen() {
        H2 header = new H2("Welcome to the Ring Actor");
        Text text = new Text("How many actors do you want the ring to be of?");

        header.addClassName("ring-header");
        return new VerticalLayout(header, status, new HorizontalLayout(text, nActors, submit));
    }

    public void createRing(int n) {

        //TODO ARREGLAR FUNCIONAMIENTO
        RingActor ra = new RingActor();
        UI ui = UI.getCurrent();
        ActorProxy ringActor = ListView.context.spawnActor("ring", ra);
        ActorProxy ap = null, temp = ringActor;
        int i = 0;
        boolean first = false;
        Message tOsend = new Message(temp, "Hello RING ACTOR");

        for (i = 1; i <= n; i++) {
            if (!first) {
                ap = ListView.context.spawnActor("ring" + i, new RingActor());
                ra.setNext(ap);
            }
            tOsend = new Message(temp, tOsend.getText());


            ap.send(tOsend);
            Notification notification = Notification.show(tOsend.toString(), 5000, Notification.Position.BOTTOM_END);
            notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
            temp = ap;
            //tOsend=temp.getActor().getQueue().element();
            ra = (RingActor) ap.getActor();
        }
        first = true;
        tOsend = new Message(temp, tOsend.getText());
        //ringActor.send(tOsend);
        ap = ringActor;


    }
}
