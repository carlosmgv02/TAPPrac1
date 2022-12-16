import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Decorator.EncryptionDecorator;
import ActorModel.Decorator.LambdaFirewallDecorator;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

class LambdaFirewallDecoratorTest {

    AbstractContextFactory factory = new PlatformContextFactory();
    AbstractContext context = factory.create();

    Predicate<Message> filter;
    ActorProxy actor, actor2;
    LambdaFirewallDecorator lambda;

    @BeforeEach
    public void initialize() {


    }

    @RepeatedTest(20)
    public void shouldBeProcessed() {
        filter = msg -> ((msg!=null && msg.getFrom()!=null)?msg.getFrom().getProxyId().equals("encryption"):false);
        Actor act=new InsultActor();
        lambda = new LambdaFirewallDecorator(act);
        lambda.addClosureMessage(filter);
        actor = context.spawnActor("actor1", lambda);
        actor2 = context.spawnActor("encryption", lambda);
        try {
            actor.send(new Message(actor2, "Missatge de prova per al test"));
            Assert.assertNotNull(act.process());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldNtBeProcessed() {
        boolean nonExisting= true;
        filter=msg->((msg!=null && msg.getFrom()!=null)?msg.getFrom().getProxyId().equals("NONEXISTING"):false);
        lambda=new LambdaFirewallDecorator(new InsultActor());
        lambda.addClosureMessage(filter);
        actor.send(new Message(actor2,"Message not going to be processed"));

    }
}