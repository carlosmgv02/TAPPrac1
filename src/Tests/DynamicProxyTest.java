import ActorModel.ActorProxy;
import ActorModel.DynamicProxy.DynamicProxy;
import ActorModel.DynamicProxy.InsultService;
import ActorModel.DynamicProxy.InsultServiceImpl;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.PlatformContextFactory;
import ActorModel.InsultActor;
import ActorModel.Messages.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class DynamicProxyTest {
    ActorProxy prox;
    InsultService insultService;
    AbstractContextFactory factory;
    AbstractContext context;

    @BeforeEach
    public void init() {
        factory=new PlatformContextFactory();
        context=factory.create();
        prox=context.spawnActor("ins",new InsultActor());
        insultService = (InsultService) DynamicProxy.intercept(new InsultServiceImpl(), prox );
    }
    @RepeatedTest(20)
    public void interceptGetMessage(){
        init();
        Assert.assertNotNull(insultService.getInsult());
    }
    @RepeatedTest(4)
    public void interceptGetAllMessages(){
        //init();
        Message o=insultService.getAllInsults();
        System.out.println(o);
        Assertions.assertTrue(o.toString().contains("feo"));
    }
}
