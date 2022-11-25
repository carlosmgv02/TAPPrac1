package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.LinkedList;
import java.util.Queue;

public class ActorDecorator extends Actor {

    @Override
    public void send(Message msg) {
        cua.offer(msg);
    }

    @Override
    public Message process() {
        Message processedMessage;
        processedMessage = cua.element();
        cua.poll();

        return processedMessage;
    }




    @Override
    public void run() {

    }

    @Override
    public void start() {

    }
}
