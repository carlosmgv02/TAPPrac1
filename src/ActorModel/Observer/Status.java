package ActorModel.Observer;

/**
 * Enum used to define the different status of an actor.
 */
public enum Status {
    /**
     * The actor is running.
     */
    CREATED,
    /**
     * The actor has been interrupted.
     */
    STOPPED,
    /**
     * There has been an error.
     */
    ERROR,
    /**
     * The actor has received a message.
     */
    MESSAGE
}