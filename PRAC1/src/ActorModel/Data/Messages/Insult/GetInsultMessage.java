package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;
import ActorModel.Data.Messages.Message;

public class GetInsultMessage extends Message {
    public GetInsultMessage(Actor from, String message) {
        super(from, message);
    }

}
