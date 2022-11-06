package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;
import ActorModel.Data.Messages.Message;

public class InsultMessage extends Message {
    public InsultMessage(Actor from, String message) {
        super(from, message);
    }
}

