package ActorModel.Data;

import ActorModel.Data.Messages.Insult.AddInsultMessage;
import ActorModel.Data.Messages.Insult.GetInsultMessage;
import ActorModel.Data.Messages.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaFirewallDecorator extends FirewallDecorator{

    private List<Message> messages = new ArrayList<>();
    public LambdaFirewallDecorator(Actor act) {
        super(act);
    }
    public void addClosure(Message msg){
        Lambda lm=(message)->{
            if(!messages.contains(message))
                messages.add(message);
        };
    }


}
