
import ActorModel.Actor;
import ActorModel.HelloWorldActor;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class used to test the HelloWorld actor
 */
public class HelloWorldTest {
    /**
     * Method used to test the hello world actor.
     * <p>
     * As the hello world actor only prints a message, we will test it by redirecting the output to a file.
     * </p>
     *
     * @throws IOException exception is thrown in case we can't read the file.
     */
    @Test
    public void helloWorldTest() throws IOException, InterruptedException {
        System.out.println("-> TESTING HELLO WORLD ACTOR...");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Actor act = new HelloWorldActor();
        act.process();
        String str = out.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        str = str.replace("\n", "").replace("\r", "");
        assertEquals("From Hello World Actor: ", str);

    }
}
