package ActorModel.Messages;

import java.lang.reflect.Method;

public class ReflectiveMessage extends Message {
    private Method called;
    private Object[] params;
}
