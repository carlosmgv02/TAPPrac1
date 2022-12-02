package ActorModel.Data.Messages;

import ActorModel.Data.Actor;
import ActorModel.Data.ActorProxy;

/**
 * Class that represents a message to be sent to an actor.

 *     This class will be used to send messages to the actors and will have different implementations:
 *     <ul>
 *         <li>{@link Message}</li>
 *         <li>{@link ActorModel.Data.Messages.Insult.GetInsultMessage}</li>
 *         <li>{@link ActorModel.Data.Messages.Insult.GetAllInsultsMessage}</li>
 *         <li>{@link ActorModel.Data.Messages.Insult.AddInsultMessage}</li>
 *     </ul>

 */
public class Message {
    protected ActorProxy from;

    private String text;

    /**
     * Class constructor
     * @param from the actor that sends the message
     * @param message the message to be sent
     */
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

    /**
     * Text setter
     * @param text the text to be set
     */
    public void setText(String text){
        this.text=text;
    }

    /**
     * Method that returns the source of the message
     * @return the source of the message
     */
    public ActorProxy getFrom(){
        return from;
    }

    /**
     * Method that sets the source of the message
     * @param source the source of the message
     */
    public void setFrom(ActorProxy source){
        from=source;
    }

    /**
     * Equals override to make to messages equal if they have the same text and source
     * @param obj the object to be compared
     * @return true if the messages are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())&&getFrom()!=null){
            return ((Message) obj).getFrom().equals(this.getFrom())&&((Message) obj).getText().equals(this.getText());
        }
        return super.equals(obj);
    }

    /**
     * Method that returns the object's toString
     * @return the object's toString
     */
    @Override
    public String toString(){
        return "Message from "+from.getProxyId()+" : "+text;
    }
}
