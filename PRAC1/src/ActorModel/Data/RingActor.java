package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

public class RingActor extends Actor {
    Actor next;


    @Override
    public Message receive() {
        return null;
    }

    @Override
    public String process() {
        System.out.println("Printing Ring Actor");
        cua.poll();
        return null;
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    @Override
    public Queue<String> getQueue() {
        return null;
    }


    //

    public void connectProxies() {

    }



}
