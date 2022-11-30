package ActorModel.Data.Tests;

import ActorModel.Data.ActorContext;
import ActorModel.Data.ActorProxy;
import ActorModel.Data.HelloWorldActor;
import ActorModel.Data.InsultActor;
import ActorModel.Data.Messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorProxyTest {
    @Test
    public void messageQueueShouldBeOne(){
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD BE ONE...");
        Message msg=new Message(null,"Hello World");
        ActorProxy proxy= ActorContext.spawnActor("prueba",new InsultActor());
        proxy.send(msg);
        assertEquals(true,ActorContext.lookup("prueba").getQueLength()==1);
    }
    @Test
    public void messageQueueShouldContain(){
        System.out.println("-> TESTING MESSAGE QUEUE SHOULD CONTAIN...");
        Message msg=new Message(null,"Hello World");
        ActorProxy proxy= ActorContext.spawnActor("prueba",new InsultActor());
        proxy.send(msg);
        assertEquals(true,ActorContext.lookup("prueba").getQueue().contains(msg));
    }


}