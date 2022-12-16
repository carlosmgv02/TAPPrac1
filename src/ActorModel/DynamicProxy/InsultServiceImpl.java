package ActorModel.DynamicProxy;

import ActorModel.Messages.Message;

/**
 * Class used to implement the InsultService interface.
 * <p>
 *     Methods won't do anything because messages will be intercepted.
 *     They just have a print to show that it won't be called.
 * </p>
 */
public class InsultServiceImpl implements InsultService {
    @Override
    public void addInsult(String insult) {
        System.out.println("Hola addInsult");
    }

    @Override
    public Message getInsult() {
        System.out.println("Hola getInsult");
        return null;
    }

    @Override
    public Message getAllInsults() {
        System.out.println("Hola getAllInsults");
        return null;
    }
}
