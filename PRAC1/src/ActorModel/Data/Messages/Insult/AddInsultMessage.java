package ActorModel.Data.Messages.Insult;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Message;

public class AddInsultMessage extends Message {
    private String insult;
    public AddInsultMessage (Actor actor, String insult){
        super(actor,insult);
    }

    /*
    public Actor getReciver() {
        return reciver;
    }
    public void setReciver(Actor reciver) {
        this.reciver = reciver;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public Actor getSender() {
        return sender;
    }
    public void setSender(Actor sender) {
        this.sender = sender;
    }

     */

    @Override
    public String toString(){
        return "AddInsultMessage{" + "msg=' " + insult + '\'' + '}';
    }
}

