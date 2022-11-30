package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public class ActorDecorator extends Actor {
    
    @Override
    public Message process() {
        Message processedMessage;
        processedMessage = cua.element();
        cua.poll();
        return processedMessage;
    }

}
