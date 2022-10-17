package mickimaus.Data;

public class Message {
    private Actor from;
    private String text;
    public Message(Actor from,String message){
        this.from=from;
        this.text=message;
    }
    public String getText(){
        return text;
    }
}
