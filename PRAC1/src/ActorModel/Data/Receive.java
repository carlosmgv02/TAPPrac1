package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public interface Receive {
    Message receive();
}
