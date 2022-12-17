import ActorModel.ActorProxy;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.Factory.VirtualContextFactory;
import ActorModel.Messages.Message;
import ActorModel.RingActor;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;

public class RingActorTest {

    final AbstractContextFactory factory = new VirtualContextFactory();
    final AbstractContext context = factory.create();


    ActorProxy ringActor;

    @Before
    public void initialize() {
        ringActor = context.spawnActor("ring", new RingActor());
    }

    @ParameterizedTest
    @ValueSource(longs = {100})
    public void testMessageSent(long n) {
        //TODO ARREGLAR FUNCIONAMIENTO
        RingActor ra = new RingActor();
        ActorProxy ringActor = context.spawnActor("ring", ra);
        ActorProxy ap = null, temp = ringActor;
        int i = 0;
        boolean first = false;
        Message tOsend = new Message(temp, "Hello RING ACTOR");
        for (int j = 0; j < n; j++) {
            for (i = 1; i <= n; i++) {
                if (!first) {
                    ap = context.spawnActor("ring" + i, new RingActor());
                    ra.setNext(ap);
                }
                tOsend = new Message(temp, tOsend.getText());
                ap.send(tOsend);
                temp = ap;
                //tOsend=temp.getActor().getQueue().element();
                ra = (RingActor) ap.getActor();
            }
            first = true;
            tOsend = new Message(temp, tOsend.getText());
            //ringActor.send(tOsend);
            ap = ringActor;

        }
        assertEquals(ringActor.getActor().getQueue().element().getFrom(), temp);
    }
}
