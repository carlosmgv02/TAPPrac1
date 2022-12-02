package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public class AuxProxy {
    private final ActorProxy actorProxy;
    public AuxProxy(ActorProxy actorProxy){
        this.actorProxy=actorProxy;
    }
    public void send(Message m){
        //TODO
        if(actorProxy!=null)
            actorProxy.offer(m);
    }
    public ActorProxy getActorProxy(){
        return actorProxy;
    }
}
