package ActorModel.DynamicProxy;

import ActorModel.ActorProxy;
import ActorModel.Messages.Insult.AddInsultMessage;
import ActorModel.Messages.Message;

import java.lang.reflect.*;

/**
 * Class used to intercept calls to an interface and convert them into messages
 */
public class DynamicProxy implements InvocationHandler {

    private static ActorProxy actor;

    private Object target = null;

    private DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * Method to intercept the Actor
     * @param target
     * @param ap
     * @return Object
     */
    public static Object intercept(Object target, ActorProxy ap) {
        Class<? extends Object> targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();
        actor = ap;
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target));
    }

    /**
     * This method is called when a method is invoked on a proxy instance of the class we've previously set as target.
     *
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.  The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;
        Message msg=null;
        String str = "";
        Class cla = null;
        Object obj = null;
        try {
            if (args != null)
                str = args[0].toString();

            switch (method.getName()) {
                case "addInsult":
                    cla = Class.forName("ActorModel.Messages.Insult.AddInsultMessage");
                    Constructor cons = cla.getDeclaredConstructor(String.class);
                    obj = cons.newInstance(str);
                    break;
                case "getInsult":
                    cla = Class.forName("ActorModel.Messages.Insult.GetInsultMessage");
                    obj = cla.newInstance();
                    break;
                case "getAllInsults":
                    cla = Class.forName("ActorModel.Messages.Insult.GetAllInsultsMessage");
                    obj = cla.newInstance();
                    break;
                default:
                    break;
            }
            actor.send((Message) obj);
            Thread.sleep(10);

            if (!(obj instanceof AddInsultMessage)) {
                msg = actor.receive();
                //System.out.println("Proxy response: \n" + msg);
            }
        } catch (InvocationTargetException ite) {
            throw ite.getTargetException();
        } catch (Exception e) {
            System.err.println("Invocation of " + method.getName() + " failed");
        } finally {
            return msg;
        }
    }
}

