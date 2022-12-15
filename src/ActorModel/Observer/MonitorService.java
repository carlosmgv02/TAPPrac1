package ActorModel.Observer;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.Messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Decorator class for the ActorContext. It adds the ability to monitor the actors and their traffic.
 * <p>
 * This class will allow us to monitor the message sending between actors and the traffic of each actor.
 * We'll also implement the observer pattern to notify every observer when a change in the status of an actor occurs.
 * </p>
 *
 * @see ActorContext
 * @see Observer
 * @see Status
 * @see Traffic
 */
public class MonitorService extends ActorContext {

    private static final Map<Actor,Integer>numberOfMessages= new HashMap<>();
    private static final Map<Actor, ArrayList<Message>> sentMessages = new HashMap<>();
    private static final Map<Actor, ArrayList<Message>> receivedMessages = new HashMap<>();
    private static final Map<Actor, ArrayList<Observer>> observerList = new HashMap<>();
    private static final Map<Status, ArrayList<Actor>> status = new HashMap<>();
    private static final Map<Traffic, ArrayList<Actor>> traffic = new HashMap<>();


    public MonitorService() {
        super();
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

    /**
     * Method used to update an actor's status
     *
     * @param a    the actor to update
     * @param stat the status to set
     */
    public static void setStatus(Actor a, Status stat) {
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
     *
     * @param act    the actor to notify
     * @param update the status of the actor
     */
    private static void notifyAllObservers(Actor act, Status update) {
        for (Observer observer : observerList.get(act)) {
            observer.update(update);
        }
    }

    public int getNumberOfMessages(Actor a) {
        return a.getQueLength();
    }

    /**
     * Method used to monitor the actor passed by parameter.
     * AKA ATTACH
     *
     * @param actor the actor we want to monitor.
     */
    public void monitorActor(Actor actor) {
        if (!observerList.containsKey(actor))
            observerList.put(actor, new ArrayList<>());
    }

    /**
     * This methods monitors all the actors
     */
    public void monitorAllActors() {
        for (Actor a : ActorContext.getActors())
            observerList.put(a, new ArrayList<>());
    }

    /**
     * Method used to update an actor's traffic
     *
     * @param a the actor to update
     */
    public void setTraffic(Actor a) {
        var queue = a.getQueLength();
        Traffic st = null;
        if (queue < 5)
            st = Traffic.LOW;
        else if (queue > 5 && queue < 15)
            st = Traffic.MEDIUM;
        else if (queue > 15)
            st = Traffic.HIGH;

        if (traffic.containsKey(st)) {
            traffic.get(st).add(a);
        } else {
            ArrayList<Actor> list = new ArrayList<>();
            list.add(a);
            traffic.put(st, list);
        }
    }
    public static void addActorMessage(Actor actor,Message msg){
        if(sentMessages.containsKey(actor)){
            sentMessages.get(actor).add(msg);
        }else{
            ArrayList<Message> list = new ArrayList<>();
            list.add(msg);
            sentMessages.put(actor,list);
        }
    }
    public static void addReceivedMessage(Actor from,Message msg){
        if(receivedMessages.containsKey(from)){

            receivedMessages.get(from).add(msg);
        }
        else{
            ArrayList<Message> list = new ArrayList<>();
            list.add(msg);
            receivedMessages.put(from,list);
        }
    }
    public static void getAllSentMessages(){
        sentMessages.forEach((k,v)->{
            //System.out.println("Actor: "+k);
            v.forEach(System.out::println);
        });
    }
    public static  Map<Actor, ArrayList<Message>> getAllReceivedMessages(){
        return receivedMessages;
    }


}