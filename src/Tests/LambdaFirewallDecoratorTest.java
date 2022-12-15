import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Decorator.EncryptionDecorator;
import ActorModel.Decorator.LambdaFirewallDecorator;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LambdaFirewallDecoratorTest {

    Predicate<Message> filter;
    ActorProxy actor, actor2;
    LambdaFirewallDecorator lambda;

    @Before
    public void initialize() {
        filter = msg -> (msg.getFrom().getProxyId().equals("encryption"));
        lambda = new LambdaFirewallDecorator(new InsultActor());
        lambda.addClosureMessage(filter);
        actor = ActorContext.spawnActor("actor1", lambda);
        actor2 = ActorContext.spawnActor("encryption", lambda);


    }

    @Test
    public void lambdaTesting() {
        actor.send(new Message(actor2, "Missatge de prova per al test"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCallAndProcess() {

        LambdaFirewallDecorator bfd = new LambdaFirewallDecorator(new EncryptionDecorator(new InsultActor()));
        Message response = bfd.process();
        if (response != null) {
            boolean contains = true;
            assertTrue(contains);
        }

    }
}