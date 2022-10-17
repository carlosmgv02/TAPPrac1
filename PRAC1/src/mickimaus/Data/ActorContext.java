package mickimaus.Data;

import mickimaus.Data.Actor;

import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ActorContext {
    private HashMap<String,Actor>actorSet=new HashMap<>();
    private static ActorContext actorInstance;
    private ActorContext(){

    }
    public synchronized static ActorContext getInstance(){
        if(actorInstance==null){
            actorInstance=new ActorContext();
        }
        return actorInstance;
    }

    public ActorProxy spawnActor (String name, Actor type){
        ActorProxy newActor = new ActorProxy();
        actorSet.put(name, type);
        return newActor;
    }




}
