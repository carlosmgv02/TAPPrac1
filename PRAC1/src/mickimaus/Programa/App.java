package mickimaus.Programa;

import mickimaus.Data.*;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        ActorContext ac = ActorContext.getInstance();
        ActorProxy hola = ac.spawnActor("prova1", new RingActor());
        hola.send(new Message(null, "Hello wol"));
        hola.send(new Message(null, "Hellouu"));

        //System.out.println(hola.getQueLength());

        hola.getQue().forEach((s)-> System.out.println(s));



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