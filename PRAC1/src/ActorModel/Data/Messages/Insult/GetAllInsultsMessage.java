package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;
import ActorModel.Data.Messages.Message;

public class GetAllInsultsMessage extends Message {
    public GetAllInsultsMessage(Actor from, String message) {
        super(from, message);
    }
}

