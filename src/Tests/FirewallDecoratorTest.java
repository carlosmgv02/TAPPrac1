import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Decorator.FirewallDecorator;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirewallDecoratorTest {

    /**
     * Class used to test Firewall Decorator
     * <p>
     * It will be used to test the decorator by passing a message to an actor and checking if the message comes from a proxy or not
     * </p>
     *
     * @see ActorModel.Decorator.EncryptionDecorator
     */

    ActorContext actorContext;
    ActorProxy actor;
    Message msg = null;

    /**
     * This method is used to create the actor context and the actor proxy
     *
     * @Before is used to execute this method before each test
     */
    public void create(){
        actor = ActorContext.spawnActor("firewall", new FirewallDecorator(new InsultActor()));
        actorContext = ActorContext.getInstance();
        msg = new Message(null, "mensaje");
    }

    /**
     * This method checks if the message comes from a proxy or not
     */
    public void shouldNotBeProxy(){
        actor.send(msg);
        assertFalse(msg.getFrom() instanceof ActorProxy);
    }

    @Test
    void process() {
        actor.send(msg);
    }

}