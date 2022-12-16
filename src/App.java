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
public class App {
    //public static PlatformContext act=PlatformContext.getInstance();
    public static void main(String[] args) {
        //TODO: VALIDATION (TESTING & JAVADOC)


        AbstractContextFactory factory = new VirtualContextFactory();

        AbstractContext context = factory.create();

        ActorProxy proxy = context.spawnActor("proxy", new FirewallDecorator(new EncryptionDecorator(new InsultActor())));
        MonitorService.attach(proxy.getActor(), new ActorListener());

        ActorProxy sender = context.spawnActor("proxy", new HelloWorldActor());
        proxy.send(new Message(sender, "holaa1"));
        proxy.send(new Message(sender, "holaa2"));
        proxy.send(new Message(sender, "holaa3"));
        proxy.send(new Message(sender, "holaa4"));
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
        System.out.println(MonitorService.getAllSentMessages(null));

//        MonitorService ms = new MonitorService();
//
//        Actor insultProva = new InsultActor();
//        ActorProxy insultActor = act.spawnActor("Thread3", insultProva);
//
//        MonitorService.attach(insultProva, new ActorListener());
//
//        MonitorService.addActorMessage(insultProva, new Message(null, " trial msg from t1"));
//        insultActor.send(new Message(null, " trial msg from t1, 2"));
//        MonitorService.getAllReceivedMessages();
        //RingActor ra = createRingActor(3);
          /*
        ActorProxy prox = ActorContext.spawnActor("ins", new InsultActor());

        InsultService o = (InsultService) DynamicProxy.intercept(new InsultServiceImpl(), prox);
        o.getAllInsults();
        o.addInsult("You are a bad person");
        o.getInsult();



        MonitorService monitor = new MonitorService();


        Actor actor = new InsultActor();
        monitor.monitorActor(actor);
        MonitorService.setStatus(actor, Status.MESSAGE);
        MonitorService.attach(actor, new ActorListener());
        */
        /*
        System.out.println("****************");
        MonitorService.getAllSentMessages();
        System.out.println("****************");
        MonitorService.getAllReceivedMessages();
*/
    }
}

