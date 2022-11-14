package ActorModel.Data.Messages.Insult;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Message;

public class AddInsultMessage extends InsultMessage {
    private String message;
    private Actor sender;
    private Actor reciever;
    public AddInsultMessage(Actor actor, String message) {
        this.reciever = actor;
        this.message = message;
    }
    public Actor getReciever() {
        return reciever;
    }
    public void setReciever(Actor a) {
        this.reciever = reciever;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Actor getSender() {
        return sender;
    }
    public void setSender(Actor a) {
        this.sender = sender;
    }
}

