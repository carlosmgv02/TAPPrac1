import ActorModel.Actor;
import ActorModel.ActorProxy;
import ActorModel.Decorator.LambdaFirewallDecorator;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

class LambdaFirewallDecoratorTest {

    final AbstractContextFactory factory = new PlatformContextFactory();
    final AbstractContext context = factory.create();

    Predicate<Message> filter;
    ActorProxy actor, actor2;
    LambdaFirewallDecorator lambda;

    @RepeatedTest(20)
    public void shouldBeProcessed() {
        filter = msg -> (msg != null && msg.getFrom() != null && msg.getFrom().getProxyId().equals("encryption"));
        Actor act=new InsultActor();
        lambda = new LambdaFirewallDecorator(act);
        lambda.addClosureMessage(filter);
        actor = context.spawnActor("actor1", lambda);
        actor2 = context.spawnActor("encryption", lambda);
        try {
            actor.send(new Message(actor2, "Missatge de prova per al test"));
            Assertions.assertNotNull(act.process());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldNtBeProcessed() {
        boolean nonExisting= true;
        filter=msg->(msg != null && msg.getFrom() != null && msg.getFrom().getProxyId().equals("NONEXISTING"));
        lambda=new LambdaFirewallDecorator(new InsultActor());
        lambda.addClosureMessage(filter);
        actor.send(new Message(actor2,"Message not going to be processed"));

    }
}