import ActorModel.Data.*;
import ActorModel.Data.Messages.Insult.*;
import ActorModel.Data.Messages.Message;


import java.util.*;

/**
 * Class that represents the main program.
 * <p>
 *     This class will create the actors and the proxies and will send messages to the actors.
 * </p>
 */
public class App {
    public static void main(String[] args) {
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



        EncryptionDecorator brrr =new EncryptionDecorator(new InsultActor());
        FirewallDecorator micimaus =new FirewallDecorator(brrr);
        LambdaFirewallDecorator lm=new LambdaFirewallDecorator(micimaus);
        ActorProxy act2=ActorContext.spawnActor("micimaus",micimaus);
        ActorProxy ac1=ActorContext.spawnActor("prueba",new InsultActor());
        act2.send(new GetAllInsultsMessage());
 */
        //Testing dynamic proxy -> proxy constructed at runtime (not compiletime )

        ActorProxy insult = ActorContext.spawnActor(  "provaDynProx",new InsultActor());
        InsultService insulter = DynamicProxy.intercept(insult);
        insulter.addInsult("stupid");
        System.out.println(insulter.getInsult());


    }

    /**
     * Method used to test the sendActor method
     */
    public static void provarSendActor(){
        ActorProxy ac1=ActorContext.spawnActor("carlos",new InsultActor());
        ActorProxy ac2=ActorContext.spawnActor("genis",new InsultActor());

        ac1.send(new Message(null,"hola buenos dias"));
        ac2.send(new Message(null,"hola buenos dias"));
    }

    /**
     * Method used to test the Actor context class
     */
    public static void provarContext(){


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

    public static void provarInsultActor(){
        Actor insultProva = new InsultActor();
        ActorProxy insultActor = ActorContext.spawnActor("Thread3",insultProva );
        insultProva.start();
        insultActor.send(new Message(null, " trial msg from t1"));
        insultActor.send(new Message(null, " trial msg from t1, 2"));
        insultActor.send(new Message(null, "prova send2"));
        insultActor.send(new Message(null, "prova send3"));
        //Processes ?concurrent? messages
        //insultProva.sleep(3000);
        insultActor.send(new Message(null, " Message sent after waiting 3 seconds"));

    }

}

