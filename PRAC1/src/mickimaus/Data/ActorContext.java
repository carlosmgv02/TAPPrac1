package mickimaus.Data;

import mickimaus.Data.Actor;

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




}
