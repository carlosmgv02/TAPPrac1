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
        RingActor ra=new RingActor();
        ActorProxy ringActor=ActorContext.spawnActor("ring",ra);
        ActorProxy ap,temp=ringActor;
        int i=0;
        Message tOsend=new Message(temp,"Hello RING ACTOR");
        for(i=1;i<=1000;i++){

            ap=ActorContext.spawnActor("ring"+i,new RingActor());
            ra.setNext(ap);
            tOsend=new Message(temp,tOsend.getText());
            ap.send(tOsend);
            temp=ap;
            //tOsend=temp.getActor().getQueue().element();
            ra=(RingActor)ap.getActor();
        }
        tOsend=new Message(temp,tOsend.getText());
        ringActor.send(tOsend);
        //ActorContext.enableProcessing();
        assertTrue(ringActor.getActor().getQueue().element().getFrom().equals(temp));
    }
}
