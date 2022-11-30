package ActorModel.Data;

import ActorModel.Data.Exceptions.CannotProcessException;
import ActorModel.Data.Messages.Message;

public class FirewallDecorator extends ActorDecorator {
    Actor act;
    boolean thrown = false;
    public FirewallDecorator(Actor act) {
        this.act=act;
    }

    @Override
    public Message process() {
        Message toProcess=act.cua.element();
        if(!ActorContext.contains(toProcess.getFrom())){
            try{
                if(!thrown)
            throw new CannotProcessException(String.valueOf(act.getId()));
            }catch(CannotProcessException e){
                thrown=true;
                System.out.println(e.getMessage());
            }
            return null;
        }
        else
            return act.process();
    }
    //Sobreescribimos el método para que al añadir un mensaje a la cola, se haga en la cola de la instancia de actor
    @Override
    public void offer(Message m){
        act.offer(m);
    }

    //Sobreescribimos el metodo run para que procesemos si la instancia de actor tiene algun mensaje en cola
    @Override
    public void run(){
        do{
            if(!act.cua.isEmpty())
                process();
        }while(!act.isInterrupted());
    }
}
