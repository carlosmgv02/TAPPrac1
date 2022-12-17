package App;

import ActorModel.*;

import ActorModel.Decorator.LambdaFirewallDecorator;
import ActorModel.DynamicProxy.DynamicProxy;
import ActorModel.DynamicProxy.InsultService;
import ActorModel.DynamicProxy.InsultServiceImpl;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;


/**
 * Class that represents the main program.
 * <p>
 * This class will create the actors and the proxies and will send messages to the actors.
 * </p>
 */
public class Main {
    public static void main(String[] args) {
        //TODO: VALIDATION (TESTING & JAVADOC)
        AbstractContextFactory factory = new PlatformContextFactory();

        AbstractContext context = factory.create();
        Actor insult=new InsultActor();
        LambdaFirewallDecorator lambda=new LambdaFirewallDecorator(insult);
        ActorProxy actor = context.spawnActor("insult", new InsultActor());
        InsultService dyn= (InsultService) DynamicProxy.intercept(new InsultServiceImpl(),actor);
        dyn.getInsult();

        ActorContext.join();





    }
}

