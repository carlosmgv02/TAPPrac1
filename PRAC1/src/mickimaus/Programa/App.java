package mickimaus.Programa;

import mickimaus.Data.Actor;

public class App {
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
    }
