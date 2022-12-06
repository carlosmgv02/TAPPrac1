import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class used to test the ActorProxy class.
 */
class ActorProxyTest {
    /**
     * Method used to test the send method.
     *
     * @see ActorProxy#send(Message) ActorProxy.send
     */
    @Test
    public void messageQueueShouldBeOne() {
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD BE ONE...");
        Message msg = new Message(null, "Hello World");
        ActorProxy proxy = ActorContext.spawnActor("prueba", new InsultActor());
        proxy.send(msg);
        assertTrue(ActorContext.lookup("prueba").getQueLength() == 1);
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
        ActorProxy proxy = ActorContext.spawnActor("prueba", new InsultActor());
        proxy.send(msg);
        assertTrue(ActorContext.lookup("prueba").getQueue().contains(msg));
    }
}