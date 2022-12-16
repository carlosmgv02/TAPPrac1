package ActorModel;

import ActorModel.Messages.Message;

/**
 * Class that will test the actor functioning.
 * <p>
 * We will use this class to create linked actor and each of it will send a message to the next one.
 * </p>
 */
public class RingActor extends Actor {

    private ActorProxy next;

    /**
     * Method that processes the message
     *
     * @return the processed message
     * @see Actor#process() Actor.process
     */
    @Override
    public Message process() {
        String printing_ring_actor = "Printing Ring Actor";
        Message msg = cua.poll();
        return new Message(msg != null ? msg.getFrom() : null, printing_ring_actor);
    }

    /**
     * Method used to get the following actor in the Ring
     * @return the next actor
     */
    public ActorProxy getNext() {
        return next;
    }

    /**
     * Method used to set the next actor in the Ring
     * @param next the next actor
     */
    public void setNext(ActorProxy next) {
        this.next = next;
    }
}
