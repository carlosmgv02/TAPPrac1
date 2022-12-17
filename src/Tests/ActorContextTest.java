

import ActorModel.*;
import ActorModel.Factory.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.testng.annotations.Test;

/**
 * Class used to test the ActorContext class.
 */
public class ActorContextTest {
    final AbstractContextFactory factory=new VirtualContextFactory();
    final AbstractContext context=factory.create();
    /**
     * Method used to test the creation of an actor.
     *
     */
    @Test
    public void spawnActor() {
        AbstractContextFactory factory = new PlatformContextFactory();

        AbstractContext context = factory.create();
        System.out.println("-> TESTING SPAWN ACTOR...");

        //ActorProxy act = context.spawnActor("hola", new HelloWorldActor());
        assertTrue(ActorContext.getNames().contains("hola"));
    }

    /**
     * Method used to test the lookup method.
     *
     * @see ActorContext#lookup(String) ActorContext.lookup
     */
    @Test
    void lookup() {
        System.out.println("-> TESTING LOOKUP...");
        Actor act = new InsultActor();

        context.spawnActor("hola", act);
        assertEquals(act, ActorContext.lookup("hola"));
    }

    /**
     * Method used to test the getNames method.
     *
     * @see ActorContext#getNames() ActorContext.getNames
     */
    @Test
    void getNames() {
        System.out.println("-> TESTING GET NAMES...");
        ActorProxy act = context.spawnActor("hola", new HelloWorldActor());
        assertTrue(ActorContext.getNames().contains("hola"));
    }

    /**
     * Method used to test the getName method
     *
     * @see ActorContext#getActorName(Actor)  ActorContext.getName
     */
    @Test
    void getActorName() {
        System.out.println("-> TESTING GET ACTOR NAME...");
        Actor act = new InsultActor();
        context.spawnActor("hola", act);
        ActorContext.getInstance();
        assertEquals("hola", ActorContext.getActorName(act));
    }
}