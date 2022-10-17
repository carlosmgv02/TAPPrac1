package mickimaus.Programa;

import mickimaus.Data.*;

import java.time.Duration;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class App {

    public static void main(String[] args) {

        ActorContext ac = ActorContext.getInstance();
        ActorProxy actor1 = ac.spawnActor("Thread1", new Actor() {
            @Override
            public void send(Message msg) {

            }

            @Override
            public Message process() {
                return null;
            }

            @Override
            public int getQueLength() {
                return 0;
            }

            @Override
            public Queue<Message> getQue() {
                return null;
            }
        });
        ActorProxy actor2=new ActorProxy("Thread2");
        actor1.send(new Message(new ActorProxy("Carlos"), "Hello wol"));
        actor1.send(new Message(new ActorProxy("Nil"), "Hellouu"));
        actor1.send(new Message(new ActorProxy("Genis"), "Hello"));
        actor1.send(new Message(new ActorProxy("Pedro"), "Hello"));
        actor2.send(new Message(new ActorProxy("Juan"), "Prueba2.1"));
        actor2.send(new Message(new ActorProxy("Luis"), "Prueba2.2"));

        /*Thread t1=new Thread(actor1);
        Thread t2=new Thread(actor2);
        t1.start();
        //t1.stop();
        t2.start();
        System.out.println(actor1.process());*/

        actor1.start();
        actor2.start();

        //actor1.getQue().forEach((s)-> System.out.println(s));



    }

    }

/*


   public static void main(String[] args) {

        provaInstancia();

    }
    //fot el mateix k abans
    public static void provaInstancia(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                Actor a1=Actor.getInstance();
                System.out.println(a1.hashCode());

            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                Actor a2=Actor.getInstance();
                System.out.println(a2.hashCode());
            }
        });
        t1.start();
        t2.start();
    }
 */