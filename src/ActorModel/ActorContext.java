package ActorModel;

import ActorModel.Observer.MonitorService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that contains the actors, based on the singleton pattern
 */
public class ActorContext {
    public final static Map<String, Actor> actorSet = new HashMap<>();
    /**
     * Temporary variable used to store the actor's thread to track its behaviour
     */
    public final static Map<Actor, Thread> threadMap = new HashMap<>();

    private static ActorContext actorInstance;
    private MonitorService monitorService;

    protected ActorContext() {

    }

    /**
     * Method to return the ActorContext instance, based on the Singleton pattern
     *
     * @return the instance of the ActorContext
     */
    public synchronized static ActorContext getInstance() {
        if (actorInstance == null) {
            actorInstance = new ActorContext();
        }
        return actorInstance;
    }



    /**
     * Method to get the actor corresponding to the name
     *
     * @param id the name of the actor
     * @return the actor
     */
    public static Actor lookup(String id) {
        Actor act;

        if (actorSet.containsKey(id)) {
            act = actorSet.get(id);
            return act;
        }
        return null;
    }

    public static void enableProcessing() {
        threadMap.forEach(
                (key, value) -> {
                    value.start();
                }
        );
    }

    /**
     * Method to check if the actor is in the context
     *
     * @param a the actor to check
     * @return true if the actor is in the context, false otherwise
     */
    public static boolean contains(Actor a) {
        return actorSet.containsValue(a);
    }


    /**
     * Method to obtain the names of the actors in the context
     *
     * @return the set of names
     */
    public static Set<String> getNames() {
        //keySet()->gets the keys
        return actorSet.keySet();
    }


    /**
     * Method to obtain the name of the actor
     *
     * @param actor the actor
     * @return the name of the actor
     */
    public static String getActorName(Actor actor) {
        for (Map.Entry<String, Actor> entry : actorSet.entrySet()) {
            if (entry.getValue().equals(actor)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Method used to obtain all actors from the actor context
     *
     * @return the collection of actors
     */
    public static Collection<? extends Actor> getActors() {
        return actorSet.values();
    }
}
