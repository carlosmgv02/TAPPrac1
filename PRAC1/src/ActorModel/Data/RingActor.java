package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public class RingActor extends Actor{

    ActorProxy next;

    @Override
    public Message process() {
        String printing_ring_actor = "Printing Ring Actor";
        System.out.println(printing_ring_actor);
        cua.poll();
        return new Message(this,printing_ring_actor);
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    public void connectProxies() {

    }



}
