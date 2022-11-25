package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.function.Predicate;
/*
public class LambdaFirewallDecorator extends ActorDecorator{

    Predicate<Message> predicado;

    public void addClosure(Predicate<Message> predicado){
        this.predicado = predicado;
    }

    @Override
    public void send(Message msg) {
        if(predicado.test(msg)){
            super.send(msg);
        }
    }

    public LambdaFirewallDecorator(Predicate<Message> predicado) {
        this.predicado = predicado;
    }
}
*/