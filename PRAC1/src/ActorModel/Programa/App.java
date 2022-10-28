package ActorModel.Programa;

import ActorModel.Data.*;

import java.util.Queue;

public class App {

    public static void main(String[] args) {

        //Ini the app w the singleton
        ActorContext.getInstance();
        //Create the first proxy
        ActorProxy actor1 = ActorContext.spawnActor("Thread1", new RingActor());
        //Send the first message
        actor1.send(new Message(null, "Hello wol"));
        actor1.send(new Message(null, "ola"));
        actor1.send(new Message(null, "prova"));
        actor1.send(new Message(null, "xd"));

        actor1.start();

        //To demonstrate the Actor system, create a HelloWorldActor
        ActorProxy hwActor = ActorContext.spawnActor("Thread2", new HelloWorldActor());
        hwActor.send(new Message(actor1, " trial msg from t1"));

        hwActor.process();




    }

    }

