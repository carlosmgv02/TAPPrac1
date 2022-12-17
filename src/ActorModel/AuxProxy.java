package ActorModel;

import ActorModel.Messages.Message;

/**
 * Class used as an auxiliary proxy to send messages back to the proxy
 */
public class AuxProxy {
    private final ActorProxy actorProxy;

    /**
     * Class constructor
     *
     * @param actorProxy the actor proxy
     */
    public AuxProxy(ActorProxy actorProxy) {
        this.actorProxy = actorProxy;
    }

    /**
     * Method used to send a message to the proxy
     *
     * @param m the message to be sent
     * @see ActorProxy#send(Message) ActorProxy.send
     */
    public void send(Message m) {
        //TODO
        if (actorProxy != null)
            actorProxy.offer(m);
    }

}
