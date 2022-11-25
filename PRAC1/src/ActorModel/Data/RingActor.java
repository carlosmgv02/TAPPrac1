package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

public class RingActor extends Actor{



    @Override
    public Message process() {
        System.out.println("Printing Ring Actor");
        cua.poll();
        return null;
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    public void connectProxies() {

    }



}
