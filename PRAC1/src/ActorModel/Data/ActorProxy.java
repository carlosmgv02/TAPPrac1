package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.LinkedList;
import java.util.Queue;


public class ActorProxy extends Actor{
    private Actor a;
    private Queue<Message> cua = new LinkedList<>();
    public ActorProxy(Actor act,String id){
        this.a=act;
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
        return null;
    }

    //Method that processes the message and deletes it from the queue

    public int getQueLength(){
        return cua.toArray().length;
    }

    public synchronized Queue<Message> getQueue(){
        return this.cua;
    }

}