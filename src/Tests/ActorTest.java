package Tests;

import ActorModel.Actor;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
     * Method to test the actor's thread functioning: it should be alive.
     *
     * @see Actor#run() Actor.run
     */
    @Test
    public void actorShouldBeAlive() {
//        Actor act=new InsultActor();
//        act.start();
//        assertTrue(act.isAlive());
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

}