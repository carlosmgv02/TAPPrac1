package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.LinkedList;
import java.util.Queue;

public class ActorDecorator extends Actor {

   private Queue<Message> cua = new LinkedList<>();


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
    public int getQueLength() {
        return cua.toArray().length;
    }

    @Override
    public Queue getQueue() {
        return this.cua;
    }

    @Override
    public void run() {

    }

    @Override
    public void start() {

    }
}
