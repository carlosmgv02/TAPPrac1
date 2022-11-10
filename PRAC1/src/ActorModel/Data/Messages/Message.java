package ActorModel.Data.Messages;

import ActorModel.Data.Actor;

public class Message {
    private Actor from;
    private String text;
    public Message(){

    }
    public Message(Actor from,String message){
        this.from=from;
        this.text=message;
    }
    public String getText(){
        return text;
    }
    @Override
    public String toString(){
        return "Message from "+from+" : "+text;
    }
}
