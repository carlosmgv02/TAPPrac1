package ActorModel.Data;

import java.util.LinkedList;
import java.util.Queue;

public class HelloWorldActor  implements Actor   {

    private Queue<Message> cua = new LinkedList<>();


    @Override
    public void send(Message msg) {
    }

    @Override
    public Message process() {
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
        return 0;
    }

    @Override
    public Queue<Message> getQue() {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void start() {

    }
}
