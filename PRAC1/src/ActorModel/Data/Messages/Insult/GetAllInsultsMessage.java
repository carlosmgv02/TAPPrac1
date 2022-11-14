package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;

public class GetAllInsultsMessage extends InsultMessage {
    private String message;
    private Actor sender;
    private Actor reciever;

    public GetAllInsultsMessage() {


    }

    public Actor getSender() {
        return sender;
    }

    public Actor getReciever() {
        return reciever;
    }

    public String getMsg() {
        return message;
    }
    public void setMsg() {
        this.message = message;
    }

    public void setSender(Actor a) {
        this.sender = sender;
    }

    public void setReciever(Actor a) {
        this.reciever = reciever;
    }
}

