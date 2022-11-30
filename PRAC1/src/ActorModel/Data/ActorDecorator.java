package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public class ActorDecorator extends Actor {


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
