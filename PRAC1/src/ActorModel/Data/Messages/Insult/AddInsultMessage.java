package ActorModel.Data.Messages.Insult;

import ActorModel.Data.*;

public class AddInsultMessage extends InsultMessage {
    public AddInsultMessage(Actor from, String body) {
        super(from, body);
    }
}

