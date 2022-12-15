package ActorModel.Decorator;

import ActorModel.Actor;
import ActorModel.Messages.Message;

import java.util.function.Predicate;

/**
 * Class that represents a decorator that filters the messages.
 * <p>
 * It will filter the messages that are not accepted by the predicate, which is passed as a parameter
 * to the {@link LambdaFirewallDecorator#addClosureMessage(Predicate)}.
 * </p>
 * <p>
 * We will use the closure to filter the messages processed by the Firewall decorator.
 * </p>
 */
public class LambdaFirewallDecorator extends FirewallDecorator {
    private Predicate<Message> filter;

    /**
     * Constructor of the class
     *
     * @param act the actor to decorate
     */
    public LambdaFirewallDecorator(Actor act) {
        super(act);
    }

    /**
     * Method that adds a closure message to the actor
     *
     * @param pred the predicate to filter the messages
     */
    public void addClosureMessage(Predicate<Message> pred) {
        //System.out.println(messages.stream().filter(pred));
        this.filter = pred;
    }

    /**
     * Method that processes the message.
     * <p>
     * We will call the actor's process method and then we will filter the message
     * </p>
     *
     * @return the processed message
     * @see Actor#process() Actor.process
     */
    @Override
    public Message process() throws InterruptedException {
        Message toProcess = super.act.getQueue().poll();
        if (filter.test(toProcess)) {
            //System.out.println(toProcess);
            return super.act.process();
        }
        return null;
    }

}
