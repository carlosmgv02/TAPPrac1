package ActorModel.Data;


import ActorModel.Data.Messages.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class ActorProxy extends Actor implements Receive{
    private final Actor a;
    private final LinkedBlockingQueue<Message> receiveQueue;
    protected String id;

    public ActorProxy(Actor act,String id){
        this.a=act;
        this.id=id;
        receiveQueue = new LinkedBlockingQueue<>();
    }

    public synchronized Queue<Message> getQueue(){
        return receiveQueue;
    }

    //Each actor has a queue 4 the messages

    //Method 2 send a message to the actor

    public void send(Message msg) {
        //Inserts the specified element into the queue
        a.send(msg);

    }
    public Message receive(){
        return null;
    }

    @Override
    public Message process() {
        return a.process();
    }

    //Method that processes the message and deletes it from the queue

    public int getQueLength(){
        return receiveQueue.toArray().length;
    }

    public String getProxyId(){
        return this.id;
    }
    public Actor getActor(){
        return this.a;
    }

}