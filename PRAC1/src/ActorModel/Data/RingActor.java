package ActorModel.Data;

import java.util.Queue;

public class RingActor extends ActorProxy {

    @Override
    public void send(Message msg) {

    }

    @Override
    public Message process() {
        System.out.println("Printing Ring Actor");
        return null;
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    @Override
    public Queue getQueue() {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void start() {

    }

    //

    public void connectProxies() {

    }



}
