package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class ActorProxy implements Receive{
    private final Actor a;
    private final LinkedBlockingQueue<Message> receiveQueue;
    protected String id;

    //TODO com fer el recieve i crear el 2n proxy perque no es solapin els sends

    //llista nomes send
    //nomes el pot tenir el proxy
    //es fica a ell mateix com a sender i el actor crida al del proxy

    public ActorProxy(Actor act,String id){
        this.a=act;
        this.id=id;
        receiveQueue = new LinkedBlockingQueue<>();
    }

    public synchronized Queue<Message> getProxyQueue(){
        return receiveQueue;
    }

    @Override
    public void send(Message msg) {
        //Inserts the specified element into the queue
        a.send(msg);

    }

    //Method that processes the message and deletes it from the queue

    public String getProxyId(){
        return this.id;
    }

    public Actor getActor(){
        return this.a;
    }

    @Override
    public Message receive() {
        return null;
    }
}