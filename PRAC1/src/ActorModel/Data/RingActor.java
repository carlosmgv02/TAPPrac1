package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

public class RingActor extends Actor{



    @Override
    public void process() {
        System.out.println("Printing Ring Actor");
        cua.poll();

    }

    @Override
    public int getQueLength() {
        return 0;
    }

    public void connectProxies() {

    }



}
