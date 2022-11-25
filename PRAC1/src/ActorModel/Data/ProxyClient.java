package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProxyClient extends Thread{

    private LinkedBlockingQueue<Message> queue;
    private Actor actor;

    public ProxyClient(Actor actor){
        queue = new LinkedBlockingQueue<>();
        this.actor = actor;
    }
    @Override
    public void run(){

    }

    public void send(Message msg){
        Actor aux = msg.getFrom();
        //msg.setReceiver(aux);
        aux.getQueue().add(msg);
    }
    public LinkedBlockingQueue<Message> getQueueMsg(){
        return queue;
    }

    public Message receive(){
        try{
        return queue.take();

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Message process() {
        return null;
    }



    public Queue<Message> getQueue() {
        return queue;
    }
}
