package ActorModel.Data;

import ActorModel.Data.Messages.*;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor extends Thread{

    protected Queue<Message> cua = new LinkedBlockingQueue<>();


    //Method that processes the message and deletes it from the queue

    public int getQueLength() {
        return cua.size();
    }

    public Queue<Message> getQueue(){
        return cua;
    }
    @Override
    public void run(){
        do{
            if(!cua.isEmpty())
                process();
        }while(!isInterrupted());
    }

    public abstract Message process();

    //Método usado para añadir un mensaje a la cola del actor
    protected void offer(Message m){
        cua.offer(m);
    }

}
