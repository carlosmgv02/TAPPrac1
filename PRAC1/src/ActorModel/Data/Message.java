package ActorModel.Data;

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
<<<<<<< Updated upstream:PRAC1/src/ActorModel/Data/Message.java
=======
    public Actor getFrom(){
        return from;
    }
    public void setText(String txt){
        this.text=text;
    }
>>>>>>> Stashed changes:PRAC1/src/ActorModel/Data/Messages/Message.java
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
