package ActorModel.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActorContext {
    private final static HashMap<String,Actor>actorSet=new HashMap<>();
    private static ActorContext actorInstance;
    private ActorContext(){

    }
    public synchronized static ActorContext getInstance(){
        if(actorInstance==null){
            actorInstance=new ActorContext();
        }
        return actorInstance;
    }

    //TODO preguntar si hem de retornar Actor o ActorProxy
    public static ActorProxy spawnActor (String name, Actor type){
        ActorProxy newActor = new ActorProxy(type,name);
        //new Thread(newActor).start();
        actorSet.put(name, type);
        //type.start();

        return newActor;
    }
    public static Actor lookup(String id){
        Actor act;
        if(actorSet.containsKey(id)){
            act= actorSet.get(id);
            return act;
        }
        return null;
    }

    public static Set<String> getNames(){
        //keySet()->gets the keys
        return actorSet.keySet();
    }
    public String getActorName(Actor actor){
        for(Map.Entry<String,Actor> entry: actorSet.entrySet()){
            if(entry.getValue().equals(actor)){
                return entry.getKey();
            }
        }
        return null;
    }
    public static void getActorsContext(){
        System.out.println("The actors we have registered in the system are:\n");
        actorSet.forEach((k,v)->System.out.println("Key: "+k+" Value: "+v));
    }

    public static HashMap<String,Actor> getActorSet(){
        return actorSet;
    }

    

}
