package ActorModel.Data;

import ActorModel.Data.Messages.*;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor extends Thread{

    protected Queue<Message> cua = new LinkedBlockingQueue<>();

    /**
     * Probablement s'ha d'esborrar i ficar-ho direcament al Insult només
     */
    //public abstract Message receive();
    //Method 2 send a message to the actor
    public void send(Message msg){
        switch (msg){
            case QuitMessage m1-> interrupt();

            default -> cua.offer(msg);
        }
    }

    //Method that processes the message and deletes it from the queue
    public abstract Message process();

    public int getQueLength() {
        return cua.size();
    }

    public Queue<Message> getQueue(){
        return cua;
    }


    @Override
    public void run() {

        while (!isInterrupted()) {
            if(this instanceof ActorProxy){
                if(((ActorProxy) this).getActor().getQueLength()>0){
                    process();
                }
            } else if ((this.cua.size() > 0) ) {
                process();

            }
        }

    }

}
