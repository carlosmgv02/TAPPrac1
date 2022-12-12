package ActorModel.Observer;

import ActorModel.*;
import ActorModel.Messages.Message;

import javax.swing.plaf.nimbus.State;
import java.util.*;

/**
 * Decorator class for the ActorContext. It adds the ability to monitor the actors and their traffic.
 * <p>
 *     This class will allow us to monitor the message sending between actors and the traffic of each actor.
 *     We'll also implement the observer pattern to notify every observer when a change in the status of an actor occurs.
 * </p>
 * @see ActorContext
 * @see Observer
 * @see Status
 * @see Traffic
 */
public class MonitorService extends ActorContext {

    private static Map<Actor, ArrayList<Message>> sentMessages = new HashMap<>();
    private static Map<Actor, ArrayList<Observer>> observerList = new HashMap<>();
    private static Map<Status, ArrayList<Actor>>status=new HashMap<>();
    private static Map<Traffic, ArrayList<Actor>>traffic=new HashMap<>();


    public int getNumberOfMessages(Actor a) {
        return a.getQueLength();
    }

    /**
     * Method used to attach a new Observer to an actor
     *
     * @param act the actor to which the observer will be attached
     * @param obs the observer to attach
     */
    public static void attach(Actor act, Observer obs) {
        if (observerList.containsKey(act))
            observerList.get(act).add(obs);
        else {
            ArrayList<Observer> list = new ArrayList<>();
            list.add(obs);
            observerList.put(act, list);
        }
    }

    /**
     * Method used to detach an observer from an actor
     *
     * @param act the actor from which the observer will be detached
     * @param obs
     */
    public static void detach(Actor act, Observer obs) {
        if (observerList.containsKey(act))
            observerList.get(act).remove(obs);
    }


    public MonitorService() {
        super();
    }


    /**
     * Method used to monitor the actor passed by parameter.
     * AKA ATTACH
     *
     * @param actor the actor we want to monitor.
     */
    public void monitorActor(Actor actor) {
        if(!observerList.containsKey(actor))
            observerList.put(actor, new ArrayList<>());
    }

    /**
     * This methods monitors all the actors
     */
    public void monitorAllActors() {
        for(Actor a: ActorContext.getActors())
            observerList.put(a, new ArrayList<>());
    }

    /**
     * Method used to update an actor's traffic
     * @param a the actor to update
     */
    public void setTraffic(Actor a) {
        var queue=a.getQueLength();
        Traffic st=null;
        if(queue<5)
            st=Traffic.LOW;
        else if(queue>5 && queue<15)
            st=Traffic.MEDIUM;
        else if(queue>15)
            st=Traffic.HIGH;

        if(traffic.containsKey(st)){
            traffic.get(st).add(a);
        }
        else {
            ArrayList<Actor> list = new ArrayList<>();
            list.add(a);
            traffic.put(st, list);
        }
    }

    /**
     * Method used to update an actor's status
     * @param a the actor to update
     * @param stat the status to set
     */
    public void setStatus(Actor a, Status stat){
        if (status.containsKey(stat)) {
            status.get(stat).add(a);
        } else {
            ArrayList<Actor> list = new ArrayList<>();
            list.add(a);
            status.put(stat, list);
        }
        notifyAllObservers(a, stat);
    }

    /**
     * Method used to notify all the observers of an actor
     * @param act the actor to notify
     * @param update the status of the actor
     */
    public void notifyAllObservers(Actor act, Status update){
        for (Observer observer : observerList.get(act)) {
            observer.update(update);
        }
    }

    /**
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

    //TODO BUSCAR OTRA MANERA DE HACERLO
    public Collection<Collection<Message>> getMessageLog(ArrayList<Actor>actor){
        Collection<Collection<Message>>messageLog=new ArrayList<>();
        for(Actor act:actor){
            messageLog.add(act.getQueue());
        }
        return messageLog;
    }
**/

}