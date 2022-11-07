package ActorModel.Data;

import ActorModel.Data.Messages.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor extends Thread{

    protected Queue<Message> cua = new LinkedBlockingQueue<>();


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
        return cua.toArray().length;
    }

    public abstract Queue<Message> getQueue();


    @Override
    public void run() {

        while (!isInterrupted()) {
            if (cua.size() > 0) {
                process();
                //interrupt();
            }
        }

    }
}
