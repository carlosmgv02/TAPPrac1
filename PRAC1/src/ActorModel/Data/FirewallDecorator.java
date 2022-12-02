package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

/**
 * Class used to filter the messages processed by an actor
 * <p>
 *     It is a decorator that wraps an actor.
 * </p>
 */
public class FirewallDecorator extends Actor {
    Actor act;
    boolean thrown = false;

    public FirewallDecorator(Actor act) {
        this.act = act;
    }

    /**
     * Method that processes the message
     *
     * @return the processed message
     */
    @Override
    public Message process() {
        Message toProcess;

        toProcess = act.getQueue().element();
        if (toProcess.getFrom() != null) {
            if (ActorContext.contains(toProcess.getFrom().getActor())) {
                return act.process();
            }

        }
        if (!thrown) {
            System.out.println("Actor cannot process the message");
            thrown = true;
        }
        return null;
    }

    /**
     * Method overriten to add the message to the actor's instance queue
     *
     * @param m the message to add
     */
    @Override
    public void offer(Message m) {
        act.offer(m);
    }

    /**
     * Method overriten to return the actor's instance queue
     *
     * @return the actor's instance queue
     */
    @Override
    public Queue<Message> getQueue() {
        return act.getQueue();
    }


    /**
     * Method overriten to process the actor's instance queue
     */
    @Override
    public void run() {
        do {
            if (!act.getQueue().isEmpty())
                process();
        } while (!act.isInterrupted());
    }
}
