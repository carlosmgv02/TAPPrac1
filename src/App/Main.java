package App;

import ActorModel.*;
import ActorModel.Decorator.EncryptionDecorator;
import ActorModel.Decorator.FirewallDecorator;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.VirtualContextFactory;
import ActorModel.Messages.Message;
import ActorModel.Messages.QuitMessage;
import ActorModel.Observer.ActorListener;
import ActorModel.Observer.MonitorService;

/**
 * Class that represents the main program.
 * <p>
 * This class will create the actors and the proxies and will send messages to the actors.
 * </p>
 */
public class Main {
    public static void main(String[] args) {
        //TODO: VALIDATION (TESTING & JAVADOC)
        AbstractContextFactory factory = new VirtualContextFactory();

        AbstractContext context = factory.create();
        Actor insult=new InsultActor();
        ActorProxy proxy = context.spawnActor("proxy", new FirewallDecorator(new EncryptionDecorator(insult)));
        MonitorService.attach(proxy.getActor(), new ActorListener());

        ActorProxy sender = context.spawnActor("proxy", new HelloWorldActor());
        proxy.send(new Message(sender, "holaa1"));
        proxy.send(new Message(sender, "holaa2"));
        proxy.send(new Message(sender, "holaa3"));
        proxy.send(new Message(sender, "holaa4"));
        proxy.send((new QuitMessage()));
        proxy.send(new Message(sender, "holaa5"));
        proxy.send(new Message(sender, "holaa6"));
        proxy.send(new Message(sender, "holaa7"));
        try {
            ActorContext.threadMap.get(proxy.getActor()).join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ActorProxy act=context.spawnActor("act",new InsultActor());
        proxy.send(new Message(act,"hola"));



    }
}

