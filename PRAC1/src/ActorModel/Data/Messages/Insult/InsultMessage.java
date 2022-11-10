package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;
import ActorModel.Data.Messages.Message;

public class InsultMessage extends Message {
    protected Message insult;

    public InsultMessage(Actor from, String message) {
        super(from, message);
    }

    public InsultMessage() {

    }
    protected void addInsult(String insult){
        this.insult=new Message(null,insult);
    }
}

