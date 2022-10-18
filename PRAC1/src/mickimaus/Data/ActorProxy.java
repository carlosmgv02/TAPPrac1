package mickimaus.Data;

import java.util.LinkedList;
import java.util.Queue;

public class ActorProxy implements Actor{
    //No sabem ben be que fa pero la classe esà creada


    //Each actor has a queue 4 the messages
    private Queue<Message> cua = new LinkedList<>();

    //Method 2 send a message to the actor
    @Override
    public void send(Message msg) {
        //Inserts the specified element into the queue
        cua.offer(msg);
    }

    //Method that processes the message and deletes it from the queue
    @Override
    public Message process() {
        //- Returns the head of the queue.
        Message processedMessage;
        processedMessage = cua.element();

        //- Deletes the head of the queue.
        cua.poll();
        return processedMessage;

    }
    @Override
    public int getQueLength(){
        return cua.toArray().length;
    }
    @Override

    public Queue getQue(){
        return this.cua;
    }


}
