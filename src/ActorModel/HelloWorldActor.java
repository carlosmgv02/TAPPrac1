package ActorModel;

import ActorModel.Messages.Message;

import java.util.NoSuchElementException;

/**
 * Class used to test the actor inheritance and the return of messages to proxies
 * <p>
 *     This class will overrite the {@link Actor#process()} method to return a message to the proxy
 * </p>
 */
public class HelloWorldActor extends Actor {
    /**
     * Method that processes the message
     *
     * @return the processed message
     * @see Actor#process() Actor.process
     */
    @Override
    public Message process() {
        System.out.println("From Hello World Actor: ");
        try {
            Message processedMessage = cua.element();
            System.out.println(processedMessage);
            cua.poll();
            return processedMessage;
        } catch (NoSuchElementException ignored) {
        }
        return null;
    }


}
