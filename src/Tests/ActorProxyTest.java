import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.Factory.VirtualContextFactory;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class used to test the ActorProxy class.
 */
class ActorProxyTest {
    AbstractContextFactory factory = new PlatformContextFactory();
    AbstractContext context = factory.create();

    /**
     * Method used to test the send method.
     *
     * @see ActorProxy#send(Message) ActorProxy.send
     */
    @Test
    public void messageQueueShouldBeOne() {
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD BE ONE...");
        Message msg = new Message(null, "Hello World");
        ActorProxy proxy = context.spawnActor("prueba", new InsultActor());
        proxy.send(msg);
        assertEquals(1, ActorContext.lookup("prueba").getQueLength());
    }

    /**
     * Method used to test the send method.
     *
     * @see ActorProxy#send(Message) ActorProxy.send
     */
    @Test
    public void messageQueueShouldContain() {
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD CONTAIN...");
        Message msg = new Message(null, "Hello World");
        ActorProxy proxy = context.spawnActor("prueba", new InsultActor());
        proxy.send(msg);
        assertTrue(ActorContext.lookup("prueba").getQueue().contains(msg));
    }
}