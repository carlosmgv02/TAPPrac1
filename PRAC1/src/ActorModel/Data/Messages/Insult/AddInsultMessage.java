package ActorModel.Data.Messages.Insult;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Message;

public class AddInsultMessage extends Message {
    public AddInsultMessage(Actor from, String message) {
        super(from, message);
    }
}

