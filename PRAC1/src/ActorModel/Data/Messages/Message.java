package ActorModel.Data.Messages;

import ActorModel.Data.Actor;
import ActorModel.Data.ActorProxy;

public class Message {
    private ActorProxy from;

    private String text;

    public Message(ActorProxy from,String message){
        this.from=from;
        this.text=message;
    }
    /*
    public Message(ActorProxy from,String message){
        this.source=from;
        this.text=message;
    }*/
    public Message(){
        text="";
    }
    public String getText(){
        return text;
    }
    public ActorProxy getFrom(){
        return from;
    }
    @Override
    public String toString(){
        return "Message from "+from+" : "+text;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())&&getFrom()!=null){
            return ((Message) obj).getFrom().equals(this.getFrom())&&((Message) obj).getText().equals(this.getText());
        }
        return super.equals(obj);
    }
}
