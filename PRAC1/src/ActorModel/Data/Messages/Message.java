package ActorModel.Data.Messages;

import ActorModel.Data.Actor;

public class Message {
    private Actor from;
    private String text;

    public Message(Actor from,String message){
        this.from=from;
        this.text=message;
    }
    public Message(){
        text="";
    }
    public String getText(){
        return text;
    }
    public Actor getFrom(){
        return from;
    }
    @Override
    public String toString(){
        return "Message from "+from+" : "+text;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())){
            return ((Message) obj).getFrom().equals(this.getFrom())&&((Message) obj).getText().equals(this.getText());
        }
        return super.equals(obj);
    }
}
