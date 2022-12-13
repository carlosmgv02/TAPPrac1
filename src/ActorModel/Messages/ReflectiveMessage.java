package ActorModel.Messages;

import java.lang.reflect.Method;

public class ReflectiveMessage extends Message {
    private final Method called;
    private final Object[] params;

    public ReflectiveMessage(Method called, Object[] params) {
        this.called = called;
        this.params = params;
    }

}
