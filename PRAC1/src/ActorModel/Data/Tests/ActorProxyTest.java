//package ActorModel.Data.Tests;
//
//import ActorModel.Data.ActorProxy;
//import ActorModel.Data.HelloWorldActor;
//import ActorModel.Data.Messages.Message;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ActorProxyTest {
//
//    @Test
//    public void send() {
//        ActorProxy actorProxy = new ActorProxy();
//        actorProxy.send(new Message(null,"hola"));
//        assertEquals(1,actorProxy.getQueLength());
//    }
//
//    @Test
//    public void process() {
//        ActorProxy actorProxy = new ActorProxy();
//        actorProxy.send("Hello World");
//        assertEquals("Hello World",actorProxy.process());
//    }
//
//    @Test
//    public void getQueLength() {
//        ActorProxy actorProxy = new ActorProxy();
//        actorProxy.send("Hello World");
//        assertEquals(1,actorProxy.getQueLength());
//    }
//
//    @Test
//    public void getQueue() {
//        ActorProxy actorProxy = new ActorProxy();
//        actorProxy.send("Hello World");
//        assertEquals("Hello World",actorProxy.getQueue().peek());
//    }
//
//    @Test
//    public void getProxyId() {
//        ActorProxy actorProxy = new ActorProxy();
//        assertEquals("proxy",actorProxy.getProxyId());
//    }
//
//    @Test
//    public void getActor() {
//        ActorProxy actorProxy = new ActorProxy();
//        assertEquals(null,actorProxy.getActor());
//    }
//
//    @Test
//    public void setActor() {
//        ActorProxy actorProxy = new ActorProxy();
//        actorProxy.setActor(new HelloWorldActor());
//        assertEquals("Hello World",actorProxy.getActor().process());
//    }
//
//}