package ActorModel.Data.Tests;

import ActorModel.Data.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.testng.annotations.Test;

public class ActorContextTest {

    @Test
    void spawnActor() {
        System.out.println("-> TESTING SPAWN ACTOR...");
        ActorProxy act= ActorContext.spawnActor("hola",new HelloWorldActor());
        assertEquals(true,ActorContext.getNames().contains("hola"));
    }

    @Test
    void lookup() {
        System.out.println("-> TESTING LOOKUP...");
        Actor act=new InsultActor();
        ActorContext.spawnActor("hola",act);
        assertEquals(act,ActorContext.lookup("hola"));

    }

    @Test
    void getNames() {
        System.out.println("-> TESTING GET NAMES...");
        ActorProxy act= ActorContext.spawnActor("hola",new HelloWorldActor());
        assertEquals(true,ActorContext.getNames().contains("hola"));
    }

    @Test
    void getActorName() {
        System.out.println("-> TESTING GET ACTOR NAME...");
        Actor act=new InsultActor();
        ActorContext.spawnActor("hola",act);
        assertEquals("hola",ActorContext.getInstance().getActorName(act));
    }
}