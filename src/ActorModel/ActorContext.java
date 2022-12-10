package ActorModel;

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
    public final static Map<Runnable, Thread> threadMap = new HashMap<>();

    private static ActorContext actorInstance;

    private ActorContext() {

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
     * Method to spawn an actor
     *
     * @param name the name of the actor
     * @param type the type of the actor
     * @return the actor proxy that controls the actor
     */
    public static ActorProxy spawnActor(String name, Actor type) {

        ActorProxy newActor = new ActorProxy(type, name);
        //new Thread(newActor).start();
        actorSet.put(name, type);
        /**
         * Thread implementation has been moved from Thread extension to Runnable implementation
         */
        Thread t=new Thread(type);
//        Thread t = Thread.startVirtualThread(type); //We now create the thread manually and pass the Runnable object
        threadMap.put(type, t); //We temporarily store the thread to keep track of its behaviour
//        t.start(); //UNCOMMENT THIS LINE TO START THE THREAD AUTOMATICALLY


        return newActor;
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

    /*
    public static void getActorsContext() {
        System.out.println("The actors we have registered in the system are:\n");
        actorSet.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
    }*/

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
     * @return the collection of actors
     */
    public static Collection<? extends Actor> getActors() {
        return actorSet.values();
    }
}
