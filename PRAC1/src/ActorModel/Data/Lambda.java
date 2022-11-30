package ActorModel.Data;

import ActorModel.Data.Messages.Message;

@FunctionalInterface
public interface Lambda{
    public void addClosure(Message msg);
}
