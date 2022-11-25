package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.LinkedList;
import java.util.Queue;

public class HelloWorldActor extends Actor   {

    private Queue<Message> cua = new LinkedList<>();


    @Override
    public void send(Message msg) {
        cua.offer(msg);
    }

    @Override
    public Message process() {
        System.out.println("Printing Hello World Actor");
        //- Returns the head of the queue.
        Message processedMessage;

        processedMessage = cua.element();
        System.out.println(" processed: "+processedMessage.getText());
        //- Deletes the head of the queue.
        cua.poll();
        return processedMessage;
    }

    @Override
    public int getQueLength() {
        return cua.size();
    }

    @Override
    public Queue<Message> getQueue() {

        return cua;
    }

    @Override
    public void run() {

    }

    @Override
    public void start() {

    }
}
