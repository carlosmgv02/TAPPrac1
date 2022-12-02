package ActorModel.Data.Messages;

import ActorModel.Data.Actor;
import ActorModel.Data.ActorProxy;

public class Message {
    protected ActorProxy from;

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

    public void setFrom(ActorProxy source){
        from=source;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())&&getFrom()!=null){
            return ((Message) obj).getFrom().equals(this.getFrom())&&((Message) obj).getText().equals(this.getText());
        }
        return super.equals(obj);
    }

    public String str(){
        return "Message from "+from.getProxyId()+" : "+text;
    }
}
