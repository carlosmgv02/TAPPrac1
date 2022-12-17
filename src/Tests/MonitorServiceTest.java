import ActorModel.Actor;
import ActorModel.ActorProxy;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import ActorModel.Observer.ActorListener;
import ActorModel.Observer.MonitorService;
import ActorModel.Observer.Status;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MonitorServiceTest {
    final AbstractContextFactory factory = new PlatformContextFactory();
    final AbstractContext context = factory.create();
    @Test
    public void attachObserverTest(){
        ActorListener observer = new ActorListener();
        Actor actor=new InsultActor();
        ActorProxy proxy= context.spawnActor("proxy", actor);
        MonitorService.attach(actor,observer);
        proxy.send(new Message(null,"testMonitorService"));
        assertEquals(Status.MESSAGE,observer.getStatus());
    }

    @Test
    public void  detach(){
        ActorListener observer = new ActorListener();
        Actor actor=new InsultActor();
        MonitorService.attach(actor,observer);
        ActorProxy proxy= context.spawnActor("proxy", actor);
        proxy.send(new Message(null,"testMonitorService"));
        MonitorService.detach(actor,observer);
        assertEquals(Status.ERROR,observer.getStatus());


    }
}
