package ActorModel.Data;

import ActorModel.Data.Messages.*;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor extends Thread implements Receive{

    protected Queue<Message> cua = new LinkedBlockingQueue<>();

    //Method 2 send a message to the actor
    public void send(String msg){
        switch (msg){
            case QuitMessage m1-> interrupt();
            default -> cua.offer(msg);

        }
    }

    //Method that processes the message and deletes it from the queue
    public abstract String process();

    public int getQueLength() {
        return cua.toArray().length;
    }

    public abstract Queue<String> getQueue();



    @Override
    public void run() {
        while (!isInterrupted()) {
            if (cua.size() > 0) {
                process();
                //interrupt();
            }
        }
    }

    protected void setMessage(String msg){
        cua.add(msg);
    }
    @Override
    public Message receive() {
        return null;
    }
}
