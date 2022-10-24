package ActorModel.Data;

public class Message {
    private Actor from;
    private String text;
    public Message(ActorProxy from,String message){
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
