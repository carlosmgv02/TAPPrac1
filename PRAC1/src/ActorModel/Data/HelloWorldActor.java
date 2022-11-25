package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

public class HelloWorldActor extends Actor {
    @Override
    public void process() {
        System.out.println("From Hello World Actor: ");
        //- Returns the head of the queue.
        Message processedMessage = cua.element();
        System.out.println(" processed: "+processedMessage.getText());
        //- Deletes the head of the queue.
        cua.poll();

    }

    @Override
    public int getQueLength() {
        return cua.size();
    }

    

}
