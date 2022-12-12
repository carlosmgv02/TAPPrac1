package ActorModel.DynamicProxy;

import ActorModel.Messages.Message;

public interface InsultService {
    void addInsult(String insult);

    Message getInsult();

    Message getAllInsults();
}
