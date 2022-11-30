package ActorModel.Data.Tests;

import ActorModel.Data.*;

import ActorModel.Data.Messages.Insult.AddInsultMessage;
import ActorModel.Data.Messages.Insult.GetInsultMessage;
import ActorModel.Data.Messages.Message;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorTest {

    @Test
    public void messageQueueShouldBeOne(){
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD BE ONE...");
        Message msg=new Message(null,"Hello World");
        Actor act=new InsultActor();
        act.getQueue().add(msg);
        assertTrue(act.getQueLength() == 1);
    }
    @Test
    public void actorShouldBeAlive(){
        Actor act=new InsultActor();
        act.start();
        assertTrue(act.isAlive());
    }
    @Test
    public void actorShouldBeSleeping(){
        Actor act=new InsultActor();
        assertTrue(!act.isAlive());
    }

}