package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.LinkedList;
import java.util.Queue;


public class ActorProxy extends Actor implements Receive {
    private Actor a;
    private Queue<Message> cua = new LinkedList<>();
    protected String id;
    public ActorProxy(Actor act,String id){
        this.a=act;
        this.id=id;
    }
    @Override
    public void send(Message msg) {
        //Inserts the specified element into the queue
        a.send(msg);

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
    public String getProxyId(){
        return this.id;
    }

    @Override
    public Message receive(){
        return new Message(this,"Hello World from ActorProxy");
    }
}