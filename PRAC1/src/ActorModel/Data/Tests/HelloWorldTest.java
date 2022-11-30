package ActorModel.Data.Tests;

import ActorModel.Data.Actor;
import ActorModel.Data.HelloWorldActor;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {
    @Before
    public void redirectOut(){
    }
    @After
    public void cleanUpOut(){
    }
    @Test
    public void helloWorldTest() throws IOException {
        System.out.println("-> TESTING HELLO WORLD ACTOR...");
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Actor act=new HelloWorldActor();
        act.process();
        String str=out.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        str=str.replace("\n","").replace("\r","");
        assertEquals("From Hello World Actor: ",str);

    }
}
