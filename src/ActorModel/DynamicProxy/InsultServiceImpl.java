package ActorModel.DynamicProxy;

import ActorModel.Messages.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsultServiceImpl  implements InsultService{
    private List<String> insults=new ArrayList <>(Arrays.asList("tonto","payaso","inútil","cabezón","pringado"));
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
