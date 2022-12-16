package ActorModel.DynamicProxy;

import ActorModel.Messages.Message;

/**
 * Interface used to create a dynamic proxy for the actor
 * <p>
 *     This interface defines the methods that the proxy will intercept.
 * </p>
 */
public interface InsultService {
    void addInsult(String insult);

    Message getInsult();

    Message getAllInsults();
}
