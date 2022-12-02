package ActorModel.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that contains the actors, based on the singleton pattern
 */
public class ActorContext {
    private final static Map<String, Actor> actorSet = new HashMap<>();
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
    public String getActorName(Actor actor) {
        for (Map.Entry<String, Actor> entry : actorSet.entrySet()) {
            if (entry.getValue().equals(actor)) {
                return entry.getKey();
            }
        }
        return null;
    }


}
