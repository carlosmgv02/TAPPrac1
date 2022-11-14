package ActorModel.Data.Tests;

import ActorModel.Data.Actor;
import ActorModel.Data.ActorProxy;
import ActorModel.Data.InsultActor;
import ActorModel.Data.Messages.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


//import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

public class ActorTest {

    @Test
    void getQueLength() {
        Actor act=new InsultActor();
        Assert.assertEquals(0,act.getQueLength());
    }

    @Test
    void getQueueShouldBeEmpty() {
        Actor act=new InsultActor();
        Assert.assertEquals(true,act.getQueue().isEmpty());
    }


     @Test
    void send() {
         Actor act=new InsultActor();
         ActorProxy act2=new ActorProxy(act,"hola");
         String msg=new Message(act,"hola");
         act2.send(msg);
         System.out.println("-> TESTING SEND...");
         assertEquals(true,act.getQueue().contains(msg));
     }

     @Test
    void run() throws InterruptedException{
        /*
         int numberOfThreads = 10;
         ExecutorService service = Executors.newFixedThreadPool(10);
         CountDownLatch latch = new CountDownLatch(numberOfThreads);
         MyCounter counter = new MyCounter();
         for (int i = 0; i < numberOfThreads; i++) {
             service.execute(() -> {
                 counter.increment();
                 latch.countDown();
             });
         }
         latch.await();
         assertEquals(numberOfThreads, counter.getCount());

         */
     }
}