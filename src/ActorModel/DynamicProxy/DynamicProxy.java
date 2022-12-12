package ActorModel.DynamicProxy;

import ActorModel.Messages.Message;

import java.lang.reflect.*;


public class DynamicProxy implements InvocationHandler {


    private InsultService insultService;
    private Object target = null;

    private DynamicProxy(Object target) {
        this.target = target;
    }

    public static Object intercept(Object target) {
        Class<? extends Object> targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();

        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target));
        //return new InsultService(targetClass,interfaces,new DynamicProxy(target));
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;
        String str = "";
        Class cla = null;
        Object obj = null;
        try {
            //aquí tenemos que acceder al campo del mensaje donde guardamos el método
            System.out.println("Before method " + method.getName());
            Method temp = target.getClass().getDeclaredMethod("send", Message.class);
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
            }

            invocationResult = temp.invoke(this.target, obj);
            System.out.println("After method " + method.getName());
        } catch (InvocationTargetException ite) {
            throw ite.getTargetException();
        } catch (Exception e) {
            System.err.println("Invocation of " + method.getName() + " failed");
        } finally {
            return invocationResult;
        }
    }
}

