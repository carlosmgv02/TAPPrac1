package ActorModel.Data.Messages.Insult;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Message;

<<<<<<< Updated upstream
public class AddInsultMessage extends InsultMessage {
    public AddInsultMessage(String insult) {

=======
public class AddInsultMessage extends Message {
    private String insult;
    private Actor sender;
    private Actor receiver;

    public AddInsultMessage (Actor actor, String insult){
        this.receiver = actor;
        this.insult = insult;
    }

    public Actor getReciver() {
        return receiver;
    }
    public void setReciver(Actor reciver) {
        this.receiver = reciver;
    }
    public String getMsg(){
        return insult;
    }
    public void setMsg(String insult){
        this.insult = insult;
    }
    public Actor getSender() {
        return sender;
    }
    public void setSender(Actor sender) {
        this.sender = sender;
    }

    @Override
    public String toString(){
        return "AddInsultMessage{" + "msg=' " + insult + '\'' + '}';
>>>>>>> Stashed changes
    }
}

