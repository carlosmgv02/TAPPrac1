package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public class AuxProxy {
    private final ActorProxy actorProxy;
    public AuxProxy(ActorProxy actorProxy){
        this.actorProxy=actorProxy;
    }
    public void send(String m){
        //TODO
        if(actorProxy!=null)
     actorProxy.offer(new Message(actorProxy,m));
    }
    public ActorProxy getActorProxy(){
        return actorProxy;
    }
}
