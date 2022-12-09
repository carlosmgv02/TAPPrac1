import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Messages.Message;
import ActorModel.RingActor;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RingActorTest {
    ActorProxy ringActor;

    @Before
    public void initialize() {
        ringActor = ActorContext.spawnActor("ring", new RingActor());
    }

    @ParameterizedTest
    @ValueSource(longs = {100})
    public void testMessageSent(long n) {
        //TODO ARREGLAR FUNCIONAMIENTO
        RingActor ra = new RingActor();
        ActorProxy ringActor = ActorContext.spawnActor("ring", ra);
        ActorProxy ap = null, temp = ringActor;
        int i = 0;
        boolean first = false;
        Message tOsend = new Message(temp, "Hello RING ACTOR");
        for (int j = 0; j < n; j++) {
            for (i = 1; i <= n; i++) {
                if (!first) {
                    ap = ActorContext.spawnActor("ring" + i, new RingActor());
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
        //ActorContext.enableProcessing();
        assertEquals(ringActor.getActor().getQueue().element().getFrom(), temp);
    }
}
