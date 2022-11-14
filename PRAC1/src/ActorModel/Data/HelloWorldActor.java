package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

public class HelloWorldActor extends Actor {
    @Override
    public Message receive() {
        return null;
    }

    @Override
    public String process() {
        System.out.println("From Hello World Actor: ");
        //- Returns the head of the queue.
        String processedMessage;

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
    public Queue<String> getQueue() {

        return cua;
    }




}
