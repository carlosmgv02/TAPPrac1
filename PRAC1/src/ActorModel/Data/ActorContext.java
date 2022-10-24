package ActorModel.Data;

import java.util.HashMap;

public class ActorContext {
    private static HashMap<String,Actor>actorSet=new HashMap<>();
    private static ActorContext actorInstance;
    private ActorContext(){

    }
    public synchronized static ActorContext getInstance(){
        if(actorInstance==null){
            actorInstance=new ActorContext();
        }
        return actorInstance;
    }

    public static ActorProxy spawnActor (String name, Actor type){
        ActorProxy newActor = new ActorProxy(name);
        actorSet.put(name, type);
        return newActor;
    }
    public Actor lookup(String id){
        if(actorSet.containsKey(id)){
            return actorSet.get(id);
        }
        return null;
    }




}
