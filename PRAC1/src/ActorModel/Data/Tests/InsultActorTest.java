package ActorModel.Data.Tests;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Insult.AddInsultMessage;
import ActorModel.Data.Messages.Insult.GetInsultMessage;
import ActorModel.Data.Messages.Message;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class used to test the InsultActor class.
 */
public class InsultActorTest {
    /**
     * Method used to test the getInsultMessage functioning.
     * <p>
     *     Tipically, we won't explicitly call the process method, but we do so in the test not to deppend on threads.
     *     The purpose of the test is to print the message previously sent to the actor's queue.
     * </p>
     */
    @Test
    public void messagesToProcess(){
        ActorProxy act= ActorContext.spawnActor("prueba",new InsultActor());
        act.send(new GetInsultMessage());
        Message msg=act.getActor().process();
        System.out.println(msg.getText());
    }

    /**
     * Mehtod used to test the addInsult method.
     * @throws InterruptedException Exception thrown when the thread is interrupted.
     */
    @Test
    public void addInsult() throws InterruptedException {
        ActorProxy act=ActorContext.spawnActor("prueba",new InsultActor());
        List<String> ins= ((InsultActor)act.getActor()).getInsultList();
        act.send(new AddInsultMessage("payaso"));
        act.getActor().sleep(10);
        assertTrue(((InsultActor) act.getActor()).getInsultList().contains("payaso"));
    }
}