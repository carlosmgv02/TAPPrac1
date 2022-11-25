package ActorModel.Data.Tests;

import ActorModel.Data.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.testng.annotations.Test;

public class ActorContextTest {

    @Test
    void spawnActor() {
        ActorProxy act= ActorContext.spawnActor("hola",new HelloWorldActor());
        System.out.println("-> TESTING SPAWN ACTOR...");
        assertEquals(true,ActorContext.getNames().contains("hola"));
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