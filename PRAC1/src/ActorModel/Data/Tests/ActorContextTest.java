package ActorModel.Data.Tests;

import ActorModel.Data.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ActorContextTest {

    @Test
    void spawnActor() {
        Actor act= ActorContext.spawnActor("hola",new HelloWorldActor());
        System.out.println("-> TESTING SPAWN ACTOR...");
        Assert.assertEquals(true,ActorContext.getNames().contains("hola"));
    }

    @Test
    void lookup() {
        Actor act=new InsultActor();

    }

    @Test
    void getNames() {
    }

    @Test
    void getActorName() {
    }
}