package com.example.application.data.entity;

import ActorModel.*;
import ActorModel.Decorator.EncryptionDecorator;
import ActorModel.Decorator.FirewallDecorator;
import ActorModel.Observer.ActorListener;
import ActorModel.Observer.MonitorService;
import ActorModel.Observer.Status;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;


public class ActorEntry {
    private ActorListener listener;

    private Status status;
    private Actor actor;
    private String proxyId;
    public int messageNumber;
    private ProgressBar progressBar;
    Span estado;



    public ActorEntry(Actor actor, String proxyId) {
        this.actor = actor;
        this.proxyId=proxyId;
        this.status = Status.CREATED;

        //cambiar el color de la barra de progreso

    }


    public Span getStatusButton(){
        return estado;
    }

    public ActorEntry() {

        this.status = Status.CREATED;
        progressBar = new ProgressBar(0,10);
        progressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);
        estado=new Span(status.toString());
        estado.getElement().getThemeList().add("badge success");
    }
    public ProgressBar getProgressBar() {
        return progressBar;
    }


    public void setProgressValue(int value){
        progressBar.setValue(value);

    }
    public void setValues(String actor,String proxyId){
        setActorType(actor);
        this.proxyId=proxyId;
        this.status = Status.CREATED;
        estado=new Span(status.toString());
        estado.getElement().getThemeList().add("badge success");
    }
    public void setState(Status s){
        this.status=s;
    }
    public String getProxyId(){
        return proxyId;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status)
    {
        this.status = status;
        if(this.status==Status.STOPPED){
            estado=new Span(status.toString());
            estado.getElement().getThemeList().add("badge error small");
        }
        if(this.status==Status.CREATED){
            Span confirmed = new Span("Confirmed");
            confirmed.getElement().getThemeList().add("badge success small");
        }
        if(this.status==Status.ERROR){
            Span denied = new Span("Denied");
            denied.getElement().getThemeList().add("badge error small");
        }
    }


    public String getActorType(){
        try{
            return actor.getClass().getSimpleName();
        }catch(NullPointerException e){
            return "NotAssigned";
        }
    }
    public void setActorType(String type){
        if(type.equals("InsultActor"))
            actor=new InsultActor();
        else if(type.equals("HelloWorldActor"))
            actor=new HelloWorldActor();
        else if(type.equals("RingActor"))
            actor=new RingActor();
        else
            actor=new EncryptionDecorator(new InsultActor());
        this.status= Status.CREATED;
    }

    public ActorListener getListener() {
        return listener;
    }

    public void setListener(ActorListener listener) {
        this.listener = listener;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
        MonitorService.attach(this.actor,this.listener);
    }

    public void setProxyId(String proxyId) {
        this.proxyId = proxyId;
    }
}
