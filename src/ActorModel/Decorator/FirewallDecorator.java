package ActorModel.Decorator;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.Messages.Message;
import ActorModel.Messages.QuitMessage;

import java.util.Queue;

/**
 * Class used to filter the messages processed by an actor
 * <p>
 * It is a decorator that wraps an actor.
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
    public Message process() throws InterruptedException {
        Message toProcess;

        toProcess = act.getQueue().element();
        if (toProcess instanceof QuitMessage) {
            throw new InterruptedException();
        } else {
            if (toProcess.getFrom() != null) {
                if (ActorContext.contains(toProcess.getFrom().getActor())) {
                    return act.process();
                }
            }
            if (!thrown) {
                System.out.println("Actor cannot process the message");
                thrown = true;
            }
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
}
