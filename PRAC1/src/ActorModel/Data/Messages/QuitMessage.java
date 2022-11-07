package ActorModel.Data.Messages;

import ActorModel.Data.Actor;

public class QuitMessage extends Message {
    public QuitMessage(Actor from, String message) {
        super(from, message);
    }
}
