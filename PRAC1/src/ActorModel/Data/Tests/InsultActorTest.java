package ActorModel.Data.Tests;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Insult.AddInsultMessage;
import ActorModel.Data.Messages.Insult.GetInsultMessage;
import ActorModel.Data.Messages.Message;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsultActorTest {

    @Test
    public void messagesToProcess(){
        ActorProxy act= ActorContext.spawnActor("prueba",new InsultActor());
        act.send(new GetInsultMessage());
        Message msg=act.getActor().process();
        System.out.println(msg.getText());
        //assertEquals(msg.getFrom(), act.getActor());
    }
    @Test
    public void addInsult() throws InterruptedException {
        ActorProxy act=ActorContext.spawnActor("prueba",new InsultActor());
        List<String> ins= ((InsultActor)act.getActor()).getInsultList();
        act.send(new AddInsultMessage("payaso"));
        act.getActor().sleep(10);
        assertTrue(((InsultActor) act.getActor()).getInsultList().contains("payaso"));
    }
}