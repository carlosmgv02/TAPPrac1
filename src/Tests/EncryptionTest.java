import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Decorator.EncryptionDecorator;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test the encryption decorator
 * <p>
 * It will test the encryption decorator by sending a message to an actor and checking if the message is encrypted
 * </p>
 *
 * @see EncryptionDecorator EncryptionDecorator
 */
public class EncryptionTest {
    ActorProxy actor;
    Message msg = null;
    ActorContext act;

    /**
     * Initialization of the actor and the message
     *
     * @Before is used to execute the method before each test
     */
    @Before
    public void initialize() {
        actor = ActorContext.spawnActor("encryption", new EncryptionDecorator(new InsultActor()));
        msg = new Message(null, "hola");
        act = ActorContext.getInstance();

    }

    /**
     * This method tests wether the message has been left on the actor's queue or not.
     */
    @Test
    public void offerShouldLeaveMessageOnActorQueue() {
        //msg=new Message(null,"hola");
        actor.send(msg);
        boolean contains = actor.getActor().getQueue().contains(msg);
        assertTrue(contains);

    }

    @Test
    public void processMessage() {
        actor.send(msg);

    }
}
