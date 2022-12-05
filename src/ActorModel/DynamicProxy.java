package ActorModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private static DynamicProxy dpInstance;

    private DynamicProxy() {

    }

    public static InsultService intercept(ActorProxy insult) {
        return null;
    }

    @Override
    public Object invoke (Object proxy, Method method, Object[]args) throws Throwable {
        return null;
    }

}

