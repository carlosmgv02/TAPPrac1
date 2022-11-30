package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.NoSuchElementException;

public class HelloWorldActor extends Actor {
    @Override
    public Message process() {
        System.out.println("From Hello World Actor: ");
        //- Returns the head of the queue.
        try{

        Message processedMessage = cua.element();
        cua.poll();
        return processedMessage;
        }catch(NoSuchElementException e){
            //e.printStackTrace();
        }
        //System.out.println(" processed: "+processedMessage.getText());
        //- Deletes the head of the queue.
        return null;
    }

    @Override
    public int getQueLength() {
        return cua.size();
    }

    

}
