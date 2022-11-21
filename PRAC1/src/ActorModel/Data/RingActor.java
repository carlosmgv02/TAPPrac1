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
    public Message process() {
        System.out.println("Printing Ring Actor");
        cua.poll();
        return null;
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    @Override
    public Queue<Message> getQueue() {
        return null;
    }


    //

    public void connectProxies() {

    }



}
