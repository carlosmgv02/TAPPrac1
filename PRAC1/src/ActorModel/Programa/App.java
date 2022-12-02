package ActorModel.Programa;

import ActorModel.Data.*;
import ActorModel.Data.Messages.Insult.*;
import ActorModel.Data.Messages.Message;


import java.util.*;

/*

PREGUNTES:
-Metode recive com el fem:
  -2 proxis
  -send1 send2 ???

-com fer spawnactor sense que peti retonra actor o proxy??


 */


public class    App {
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        //provarHelloWorld();
        //provarInsultActor();
        //Actor=ActorProxy
        //ActorProxy=Actor

/*
        ActorProxy a=ActorContext.spawnActor("prueba",new InsultActor());
        a.send(new GetInsultMessage());
        a.send(new GetAllInsultsMessage());
        a.send(new AddInsultMessage("hola"));
        a.receive();
*/
        /*
        LambdaFirewallDecorator lm=new LambdaFirewallDecorator(new HelloWorldActor());
        ActorProxy a=ActorContext.spawnActor("lambda",lm);
        lm.addClosureMessage(msg->msg.getText().equals("carlos"));
        a.send(new Message(null,"carlos"));
        System.out.println();


*/


        EncryptionDecorator brrr =new EncryptionDecorator(new InsultActor());
        FirewallDecorator micimaus =new FirewallDecorator(brrr);

        ActorProxy act2=ActorContext.spawnActor("micimaus",micimaus);
        ActorProxy ac1=ActorContext.spawnActor("prueba",new InsultActor());
        act2.send(new Message(null,"hola"));

        //EncryptionDecorator ola=new EncryptionDecorator(micimaus);

        //prueba2.send(new Message(act2,"ola"));




//        lamb.addClosureMessage(msg->msg.getText().equals("TAP"));



/*
        Actor ac1=new InsultActor();
        ActorContext.spawnActor("prueba",ac1);
        ActorProxy hello=ActorContext.spawnActor("name",new InsultActor());
        hello.send(new GetInsultMessage());
        hello.getActor().sleep(1000);
        hello.receive();
        hello.send(new GetAllInsultsMessage());
        hello.getActor().sleep(1000);
        hello.send(new AddInsultMessage("tusmuertos"));

  */      /*
        hello.send(new Message(ac1,"hola"));
        hello.send(new Message(null,"hola"));
        //probarCifrado();
        ActorProxy dec=ActorContext.spawnActor("dec",new EncryptionDecorator(new InsultActor()));
        dec.send(new Message(ac1,"hola"));
*/

        //TESTING PROXY


 /*
        ProxyClient insult = ActorContext.spawnProxy("insulter",new InsultActor());
        insult.send(new AddInsultMessage(ac1, "HOLA"));
        insult.send(new GetInsultMessage());
        Message result = insult.receive();
        System.out.println(result.getText());
*/
        /*act.sleep(3000);

        //waits until next message is sent, and it processes it
        //hello.send(new Message());
        hello.send(new Message(act, "prova THREAD"));
        hello.send(new QuitMessage(act,"QUIT THREAD"));
        //act.sleep(50);
        hello.send(new Message(act, "provaknhu THREAD"));
        //hello.send(new ResumeMessage(act,"RESUME THREAD"));
        hello.send(new Message(act, "prova ULTIM THREAD"));
        */
    }

    /**
     * Method used to test the sendActor method
     */
    public static void provarSendActor(){
        ActorProxy ac1=ActorContext.spawnActor("carlos",new InsultActor());
        ActorProxy ac2=ActorContext.spawnActor("genis",new InsultActor());

        ac1.send(new Message(null,"hola buenos dias"));
        ac1.send(new Message(null,"hola buenos dias"));
    }

    /**
     * Method used to test the Actor context class
     */
    public static void provarContext(){


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
    }


    /**
     * Method used to test the HelloWorldActor
     */
    public static void provarHelloWorld(){
        HelloWorldActor hwProva = new HelloWorldActor();
        ActorProxy hwActor = ActorContext.spawnActor("Thread2",hwProva );
        hwProva.start();
        hwActor.send(new Message(null, " trial msg from t1"));
        hwActor.send(new Message(null, " trial msg from t1, 2"));
        hwActor.send(new Message(null, "prova send2"));
        hwActor.send(new Message(null, "prova send3"));
        //Processes ?concurrent? messages
    }

    /**
     * Method used to test the InsultActor
     * @throws InterruptedException
     */
    public static void provarInsultActor() throws InterruptedException {
        Actor insultProva = new InsultActor();
        ActorProxy insultActor = ActorContext.spawnActor("Thread3",insultProva );
        insultProva.start();
        insultActor.send(new Message(null, " trial msg from t1"));
        insultActor.send(new Message(null, " trial msg from t1, 2"));
        insultActor.send(new Message(null, "prova send2"));
        insultActor.send(new Message(null, "prova send3"));
        //Processes ?concurrent? messages
        insultProva.sleep(3000);
        insultActor.send(new Message(null, " Message sent after waiting 3 seconds"));

    }

    /*
    public static void probarCifrado(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el mensaje a cifrar");
        String inputStr = sc.nextLine();

        System.out.println("Introduce el valor por el cuál cada carácter se desplazará");
        int clave= Integer.valueOf(sc.nextLine());

        EncryptionDecorator enc=new EncryptionDecorator();
        String mensajeCifrado=enc.cifrar(inputStr, clave);
        System.out.println("Texto cifrado ==> " + mensajeCifrado);
        System.out.println("--------------------------------------");
        System.out.println("Texto descifrado ==> " + enc.descifrar(mensajeCifrado, clave));

        sc.close();
    }

    public static void probarPipeline (){
        ActorContext Actor = ActorContext.getInstance();
        Actor sender = ActorContext.spawnActor("actor1", new FirewallDecorator(new RingActor()));
        Actor receiver = ActorContext.spawnActor("actor2", new EncryptionDecorator());
        sender.send(new Message(receiver, "Hola"));
    }
*/
}

