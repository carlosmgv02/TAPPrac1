package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;

public interface Message {
    public Actor getSender();
    public String getMsg();
    public void setSender(Actor a);
    public Actor getReciever();
    public void setMsg();
    public void setReciever(Actor a);
}
