package com.example.application.views.list;

import ActorModel.Messages.Insult.AddInsultMessage;
import ActorModel.Messages.Insult.GetAllInsultsMessage;
import ActorModel.Messages.Insult.GetInsultMessage;
import ActorModel.Messages.Message;
import ActorModel.Messages.QuitMessage;
import ActorModel.Observer.Status;
import com.example.application.data.entity.ActorEntry;
import com.example.application.data.service.ActorService;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

public class ActorForm extends FormLayout {

    MessageInput msgInput = new MessageInput();

    Button clickSend = new Button("Send");
    Select selectInsultType = new Select();
    Button greenMessage = new Button("Enviar mensaje");
    Binder<ActorEntry> entry = new BeanValidationBinder<>(ActorEntry.class);
    TextField proxyId = new TextField("Proxy Id");
    Message msgType;

    Select<String> tipus = new Select<>();

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancell = new Button("Cancel");
    List<String> actorList = new ArrayList<>(List.of("InsultActor", "HelloWorldActor", "RingActor", "EncryptionDecorator"));
    List<String> messages = new ArrayList<>(List.of("Message", "getInsultMessage", "getAllInsultsMessage", "addInsult", "quitMessage"));
    VerticalLayout sendLayout = new VerticalLayout();
    private ActorEntry actorEntry = new ActorEntry();

    public ActorForm() {
        addClassName("contact-form");
        tipus.setLabel("Tipus d'actor");
        //binder.bindInstanceFields(this);

        entry.forField(proxyId).bind(ActorEntry::getProxyId, ActorEntry::setProxyId);
        entry.forField(tipus).bind(ActorEntry::getActorType, ActorEntry::setActorType);
        //entry.bindInstanceFields(this);
        tipus.setItems(actorList);
        tipus.setItemLabelGenerator((ItemLabelGenerator<String>) actor -> actor);

        selectInsultType.setItems(messages);
        selectInsultType.setItemLabelGenerator((ItemLabelGenerator<String>) actor -> actor);
/**
 msgInput.addSubmitListener(event -> {

 ListView.proxies.get(actorEntry).send(new Message(null,event.getValue()));
 processMessage();
 });
 **/
        clickSend.addClickListener(event -> {
            ListView.proxies.get(actorEntry).send(msgType);
            processMessage();
        });

        add(
                proxyId,
                //lastName,
                //email,
                tipus,
                //status,
                createButtonsLayout(),
                createMessageButtons()
        );
    }

    public void setActor(ActorEntry actorEntry) {
        this.actorEntry = actorEntry;
        entry.readBean(actorEntry);
        tipus.setReadOnly(false);
        proxyId.setReadOnly(false);
    }

    public void editAlreadyExistingActor(ActorEntry actorEntry) {
        hideButtons();
        greenMessage.setVisible(true);
        selectInsultType.setVisible(false);
        this.actorEntry = actorEntry;
        entry.readBean(actorEntry);
        proxyId.setReadOnly(true);
        tipus.setPlaceholder(actorEntry.getActorType());
        tipus.setReadOnly(true);

    }

    private Component createMessageButtons() {
        msgInput.setVisible(false);
        clickSend.setVisible(false);
        selectInsultType.setVisible(false);
        setListeners();
        sendLayout = new VerticalLayout(addGreenMessage(), new HorizontalLayout(selectInsultType, clickSend), msgInput);
        return sendLayout;
    }

    public void setListeners() {
        Message msg;
        selectInsultType.addValueChangeListener(e -> {

            hideButtons();
            if (e.getValue().equals("Message")) {
                msgInput.setVisible(true);
            } else if (e.getValue().equals("getInsultMessage")) {
                clickSend.setVisible(true);
                msgType = new GetInsultMessage();
//                ListView.proxies.get(actorEntry).send(new GetInsultMessage());
            } else if (e.getValue().equals("getAllInsultsMessage")) {
                clickSend.setVisible(true);
                msgType = new GetAllInsultsMessage();
                //ListView.proxies.get(actorEntry).send(new GetAllInsultsMessage());
            } else if (e.getValue().equals("addInsult")) {
                msgInput.setVisible(true);

            } else if (e.getValue().equals("quitMessage")) {
                clickSend.setVisible(true);
                ListView.proxies.get(actorEntry).send(new QuitMessage());
            }
            msgInput.addSubmitListener(event -> {
                if (e.getValue().equals("addInsult"))
                    ListView.proxies.get(actorEntry).send(new AddInsultMessage(event.getValue()));
                else if (e.getValue().equals("Message"))
                    ListView.proxies.get(actorEntry).send(new Message(null, event.getValue()));

                processMessage();
            });

        });
    }

    public void processMessage() {

        try {
            Notification.show(ListView.proxies.get(actorEntry).getActor().process().toString());
        } catch (InterruptedException ex) {
            Notification.show("Actor has been interrupted");
            ActorService.entries.get(actorEntry.getProxyId()).setStatus(Status.STOPPED);

        }
        fireEvent(new UpdateEvent(this, actorEntry));
    }

    public void hideButtons() {
        msgInput.setVisible(false);

        clickSend.setVisible(false);

    }

    public Component addGreenMessage() {
        greenMessage.addClassName("send-button");
        greenMessage.addClickListener(e -> {
            sendLayout.setVisible(true);
            selectInsultType.setVisible(true);
        });
        greenMessage.setVisible(false);
        return greenMessage;
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);  //Para darle estilo al botón
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancell.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, actorEntry)));
        cancell.addClickListener(event -> fireEvent(new CloseEvent(this)));


        save.addClickShortcut(Key.ENTER);   //Para que al dar enter se guarde el contacto
        cancell.addClickShortcut(Key.ESCAPE);   //Para que al dar escape se cancele la operación
        return new HorizontalLayout(save, cancell, delete);
    }

    private void validateAndSave() {
        try {
            entry.writeBean(actorEntry);
            fireEvent(new SaveEvent(this, actorEntry));

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    public static abstract class ActorFormEvent extends ComponentEvent<ActorForm> {
        private final ActorEntry actor;

        protected ActorFormEvent(ActorForm source, ActorEntry contact) {
            super(source, false);
            this.actor = contact;
        }

        public ActorEntry getActor() {
            return actor;
        }
    }

    public static class SaveEvent extends ActorFormEvent {
        SaveEvent(ActorForm source, ActorEntry actor) {
            super(source, actor);
        }
    }

    public static class DeleteEvent extends ActorFormEvent {
        DeleteEvent(ActorForm source, ActorEntry actor) {
            super(source, actor);
        }

    }

    public static class CloseEvent extends ActorFormEvent {
        CloseEvent(ActorForm source) {
            super(source, null);
        }
    }

    public static class UpdateEvent extends ActorFormEvent {
        UpdateEvent(ActorForm source, ActorEntry actor) {
            super(source, actor);
        }
    }
}
