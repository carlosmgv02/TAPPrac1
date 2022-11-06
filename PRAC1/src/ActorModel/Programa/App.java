package ActorModel.Programa;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Message;

import java.util.Set;

public class App {

    public static void main(String[] args) {

        Actor act=new InsultActor();
        Actor act2=new InsultActor();
        Actor act3=new InsultActor();

        Actor prova1=new InsultActor();
        prova1.start();
        Actor prova2=new InsultActor();

        ActorProxy hello = ActorContext.spawnActor("name", prova1);
        hello.send(new Message(act2, "hello world"));
        hello.send(new Message(act3, "olaa"));
        hello.send(new Message(act, "prova thread"));




        //Ini the app w the singleton
        ActorContext.getInstance();
        //Create the first proxy
        ActorProxy actor1 = ActorContext.spawnActor("Thread1", new RingActor());
        ActorProxy actor2 = ActorContext.spawnActor("provaa", new RingActor());
        ActorProxy actor3 = ActorContext.spawnActor("holaaa", new RingActor());

        //Testing method lookup
        System.out.println("Testing method lookup()");
        Actor lookedActor = ActorContext.lookup("provaa");
        System.out.println(lookedActor); //Retuns pointer!!!

        //Testing method getNames()
        Set<String> keys = ActorContext.getNames();
        System.out.println("Testing method getNames()");
        keys.forEach(System.out::println);
        //Works but doesn't follow an order...


        //Send the first message
        actor1.send(new Message(null, "Hello wol"));
        actor1.send(new Message(null, "ola"));
        actor1.send(new Message(null, "prova"));
        actor1.send(new Message(null, "xd"));
        ActorProxy prueba1 = ActorContext.spawnActor("carlos", new InsultActor());
        prueba1.send(new Message(null,"hola"));
        //prueba1.process();
        //prueba1.quitMessage();

        //actor1.start();

        //To demonstrate the Actor system, create a HelloWorldActor
        /*
        ActorProxy hwActor = ActorContext.spawnActor("Thread2", new HelloWorldActor());
        hwActor.send(new Message(actor1, " trial msg from t1"));
        hwActor.send(new Message(actor1, " trial msg from t1, 2"));
        hwActor.send(new Message(actor2, "prova send2"));
        hwActor.send(new Message(actor3, "prova send3"));
        //Processes ?concurrent? messages
        hwActor.start();
        hwActor.run();
*/
        //TESTING PROXY

        /*ActorProxy insult = ActorContext.spawnActor("insulter",new InsultActor());
        insult.send(new GetInsultMessage());
        Message result = insult.receive();
        System.out.println(result.getText());*/


    }

}

