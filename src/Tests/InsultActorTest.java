import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.InsultActor;
import ActorModel.Messages.Insult.AddInsultMessage;
import ActorModel.Messages.Insult.GetAllInsultsMessage;
import ActorModel.Messages.Insult.GetInsultMessage;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class used to test the InsultActor class.
 */

public class InsultActorTest {
    final AbstractContextFactory factory = new PlatformContextFactory();

    final AbstractContext context = factory.create();


    /**
     * Method used to test the getInsultMessage functioning.
     * <p>
     * Tipically, we won't explicitly call the process method, but we do so in the test not to deppend on threads.
     * The purpose of the test is to print the message previously sent to the actor's queue.
     * </p>
     */

    @Test
    public void messagesToProcess() {
        Message m=new GetInsultMessage();
        ActorProxy proxy = context.spawnActor("prueba", new InsultActor());
        proxy.send(m);
        assertTrue(proxy.getActor().getQueue().poll() instanceof GetInsultMessage);
    }

    /**
     * Mehtod used to test the addInsult method.
     *
     * @throws InterruptedException Exception thrown when the thread is interrupted.
     */
    @RepeatedTest(20)
    public void addInsult() throws InterruptedException {
        InsultActor insult=new InsultActor();
        ActorProxy act = context.spawnActor("prueba", insult);
        List<String> ins = ((InsultActor) act.getActor()).getInsultList();
        act.send(new AddInsultMessage("payaso"));
        Thread.sleep(250);
        assertTrue(insult.getInsultList().contains("payaso"));
    }
}