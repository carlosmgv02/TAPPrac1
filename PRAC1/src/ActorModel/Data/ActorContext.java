package ActorModel.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        ActorProxy newActor = new ActorProxy(type,name);
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

}
