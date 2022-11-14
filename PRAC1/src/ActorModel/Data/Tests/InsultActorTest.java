package ActorModel.Data.Tests;

import ActorModel.Data.Actor;
import ActorModel.Data.HelloWorldActor;
import ActorModel.Data.InsultActor;
import ActorModel.Data.Messages.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class InsultActorTest {

    @Test
    void send() {
        Actor act=new InsultActor();
        Actor source=new HelloWorldActor();
        String msg=new Message(source,"hola");
        act.send(msg);
        Assert.assertEquals(true,act.getQueue().contains(msg));
    }
}