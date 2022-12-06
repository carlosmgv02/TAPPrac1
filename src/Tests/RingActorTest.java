import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Messages.Message;
import ActorModel.RingActor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RingActorTest {
    ActorProxy ringActor;
    @Before
    public void initialize(){
        ringActor= ActorContext.spawnActor("ring",new RingActor());
    }
    @Test
    public void testMessageSent(){
        ActorProxy ap=null,temp=ringActor;
        int i;
        for(i=1;i<=10;i++){
            ap=ActorContext.spawnActor("ring"+i,new RingActor());
            ((RingActor)ringActor.getActor()).setNext(ap);
            ap.send(new Message(temp,"Hey ring"+i+", I'm "+ ActorContext.getActorName(temp.getActor())));
            temp=ap;
        }
        ringActor.send(new Message(ap,"Hello from the last ring actor"));
        assertTrue(ringActor.getActor().getQueue().element().getFrom().equals(ap));
    }
}
