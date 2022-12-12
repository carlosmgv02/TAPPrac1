import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class used to test the Actor class
 */
public class ActorTest {
    /**
     * Method used to test the send method.
     */
    @Test
    public void messageQueueShouldBeOne() {
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD BE ONE...");
        Message msg = new Message(null, "Hello World");
        Actor act = new InsultActor();
        act.getQueue().add(msg);
        assertEquals(1, act.getQueLength());
    }


    /**
     * Method to test the actor's thread functioning: it should be dead after the actor has finished
     *
     * @see Actor#run() Actor.run
     */
    @Test
    public void actorShouldBeSleeping() {
        Actor act = new InsultActor();
//        assertFalse(act.isAlive());
    }

    @ParameterizedTest
    @ValueSource(longs = {10, 100, 10000, 1000000, 5000000, 10000000, 20000000})
    public void pingPongProcessing(long num) {
        ActorProxy act1 = ActorContext.spawnActor("insult1", new InsultActor());
        ActorProxy act2 = ActorContext.spawnActor("insult2", new InsultActor());
        long n = 0L;
        long start = System.currentTimeMillis();
        long end = start + 1000;
        while (n < num) {
            act1.send(new Message(act2, "ping"));
            act2.send(new Message(act1, "pong"));
            n++;
        }
        try {
            ActorContext.threadMap.get(act1.getActor()).join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(act1.getActor().getQueLength());
    }
}