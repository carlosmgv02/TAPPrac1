package ActorModel.Data.Tests;

import ActorModel.Data.*;

import ActorModel.Data.Messages.Insult.AddInsultMessage;
import ActorModel.Data.Messages.Insult.GetInsultMessage;
import ActorModel.Data.Messages.Message;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used to test the Actor class
 */
public class ActorTest {
    /**
     * Method used to test the send method.
     */
    @Test
    public void messageQueueShouldBeOne(){
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD BE ONE...");
        Message msg=new Message(null,"Hello World");
        Actor act=new InsultActor();
        act.getQueue().add(msg);
        assertTrue(act.getQueLength() == 1);
    }

    /**
     * Method to test the actor's thread functioning: it should be alive.
     * @see Actor#run() Actor.run
     */
    @Test
    public void actorShouldBeAlive(){
        Actor act=new InsultActor();
        act.start();
        assertTrue(act.isAlive());
    }

    /**
     * Method to test the actor's thread functioning: it should be dead after the actor has finished
     * @see Actor#run() Actor.run
     */
    @Test
    public void actorShouldBeSleeping(){
        Actor act=new InsultActor();
        assertFalse(act.isAlive());
    }

}