package ActorModel.Data;

import ActorModel.Data.Messages.Message;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Actor extends Thread{

    private Queue<Message> cua = new LinkedList<>();

    //Method 2 send a message to the actor
    public abstract void send(Message msg);

    //Method that processes the message and deletes it from the queue
    public abstract Message process();

    public int getQueLength() {
        return cua.toArray().length;
    }

    public abstract Queue<Message> getQueue();

    public void quitMessage(){
        interrupt();
    }
    @Override
    public void run() {
        System.out.println("Actor " + this.getName() + " started");
        process();

    }




}
