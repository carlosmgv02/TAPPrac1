package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.LinkedList;
import java.util.Queue;

public class ActorDecorator extends Actor {


    public void send(Message msg) {
        cua.offer(msg);
    }

    @Override
    public void process() {
        Message processedMessage;
        processedMessage = cua.element();
        cua.poll();

    }




    @Override
    public void run() {

    }

    @Override
    public void start() {

    }
}
