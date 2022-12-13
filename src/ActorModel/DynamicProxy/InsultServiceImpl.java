package ActorModel.DynamicProxy;

import ActorModel.Messages.Message;

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
