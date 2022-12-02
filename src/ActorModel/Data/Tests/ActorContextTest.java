package ActorModel.Data.Tests;

import ActorModel.Data.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.testng.annotations.Test;

/**
 * Class used to test the ActorContext class.
 */
public class ActorContextTest {
    /**
     * Method used to test the creation of an actor.
     * @see ActorContext#spawnActor(String, Actor) ActorContext.spawnActor
     */
    @Test
    void spawnActor() {
        System.out.println("-> TESTING SPAWN ACTOR...");
        ActorProxy act= ActorContext.spawnActor("hola",new HelloWorldActor());
        assertEquals(true,ActorContext.getNames().contains("hola"));
    }

    /**
     * Method used to test the lookup method.
     * @see ActorContext#lookup(String) ActorContext.lookup
     */
    @Test
    void lookup() {
        System.out.println("-> TESTING LOOKUP...");
        Actor act=new InsultActor();
        ActorContext.spawnActor("hola",act);
        assertEquals(act,ActorContext.lookup("hola"));

    }

    /**
     * Method used to test the getNames method.
     * @see ActorContext#getNames() ActorContext.getNames
     */
    @Test
    void getNames() {
        System.out.println("-> TESTING GET NAMES...");
        ActorProxy act= ActorContext.spawnActor("hola",new HelloWorldActor());
        assertEquals(true,ActorContext.getNames().contains("hola"));
    }

    /**
     * Method used to test the getName method
     * @see ActorContext#getActorName(Actor)  ActorContext.getName
     */
    @Test
    void getActorName() {
        System.out.println("-> TESTING GET ACTOR NAME...");
        Actor act=new InsultActor();
        ActorContext.spawnActor("hola",act);
        assertEquals("hola",ActorContext.getInstance().getActorName(act));
    }
}