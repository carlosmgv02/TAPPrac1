package ActorModel.Observer;

import ActorModel.*;
import ActorModel.Messages.Message;

import java.util.*;


public class MonitorService implements Observable {

    //TODO PREGUNTAR SI FA FALTA TENIR UN ACTORLISTENER A PART O SI ES LO MATEIX QUE EL MONITOR SERVICE

    protected ArrayList<Actor> actor;
    protected List<Observer>observers=new ArrayList<>();
    protected Status status; //TODO PREGUNTAR SI FA FALTA TENIR AQUESTA VARIABLE
    Map<String, ArrayList<Actor>> mapTraffic = new HashMap<>();

    public Status getStatus(){
        return this.status;
    }
    public void setStatus(Status status){
        this.status = status;
    }


    /**
     * Method used to monitor the actor passed by parameter.
     * @param actor the actor we want to monitor.
     */
    public void monitorActor(Actor actor){
        this.actor.add(actor);
    }

    /**
     * This methods monitors all the actors
     */
    public void monitorAllActors(){
        this.actor.addAll(ActorContext.getActors());
    }



    public void setTrafico(Actor act){
        if(act.getQueLength()<5){
            if(mapTraffic.containsKey("LOW")) {
                mapTraffic.get("LOW").add(act);
            }else{
                mapTraffic.put("LOW", new ArrayList<>());
                mapTraffic.get("LOW").add(act);
            }
            mapTraffic.get("LOW").add(act);
        } else if(act.getQueLength()>5 && act.getQueLength()<15){
            if(mapTraffic.containsKey("MEDIUM")){
                mapTraffic.get("MEDIUM").add(act);
            } else {
                mapTraffic.put("MEDIUM", new ArrayList<>());
                mapTraffic.get("MEDIUM").add(act);
            }
        } else if(act.getQueLength()>15){
            if (mapTraffic.containsKey("HIGH")){
                mapTraffic.get("HIGH").add(act);
            } else {
                mapTraffic.put("HIGH", new ArrayList<>());
                mapTraffic.get("HIGH").add(act);
            }
        }
    }
    public Map<String, ArrayList<Actor>> getTraffic(Actor trafico) {
        mapTraffic.get(trafico);
        return mapTraffic;
    }
    public int getNumberOfMessages(Actor act){
        return act.getQueLength();
    }

    //TODO BUSCAR OTRA MANERA DE HACERLO
    public Collection<Collection<Message>> getMessageLog(ArrayList<Actor>actor){
        Collection<Collection<Message>>messageLog=new ArrayList<>();
        for(Actor act:actor){
            messageLog.add(act.getQueue());
        }
        return messageLog;
    }

    /**
     * Method used to add an observer to the list of observers.
     * @param o the observer we want to add.
     */
    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    /**
     * Method used to remove an observer from the list of observers.
     * @param o the observer we want to remove.
     */
    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Status status) {
        for(Observer o : observers){
            o.update(status);
        }
    }
}