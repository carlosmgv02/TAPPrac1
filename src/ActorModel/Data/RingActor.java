package ActorModel.Data;

import ActorModel.Data.Messages.Message;

/**
 * Class that will test the actor functioning.
 * <p>
 * We will use this class to create linked actor and each of it will send a message to the next one.
 * </p>
 */
public class RingActor extends Actor {

    ActorProxy next;

    /**
     * Method that processes the message
     *
     * @return the processed message
     * @see Actor#process() Actor.process
     */
    @Override
    public Message process() {
        String printing_ring_actor = "Printing Ring Actor";
        System.out.println(printing_ring_actor);
        Message msg = cua.poll();
        return new Message(msg != null ? msg.getFrom() : null, printing_ring_actor);
    }
}
