package ActorModel.Data;

import ActorModel.Data.Exceptions.CannotProcessException;
import ActorModel.Data.Messages.Message;

import java.util.NoSuchElementException;
import java.util.Queue;

public class FirewallDecorator extends Actor {
    Actor act;
    boolean thrown = false;
    public FirewallDecorator(Actor act) {
        this.act=act;
    }


    @Override
    public Message process() {
        Message toProcess;

        toProcess=act.getQueue().poll();
            if (toProcess.getFrom() != null) {
                if(ActorContext.contains(toProcess.getFrom().getActor())){
                    return act.process();
                }

            }
            System.out.println("Actor cannot process the message");
        return null;
    }
    //Sobreescribimos el método para que al añadir un mensaje a la cola, se haga en la cola de la instancia de actor
    @Override
    public void offer(Message m){
        act.offer(m);
    }
    @Override
    public Queue<Message> getQueue() {
        return act.getQueue();
    }

    //Sobreescribimos el metodo run para que procesemos si la instancia de actor tiene algun mensaje en cola
    @Override
    public void run(){
        do{
            if(!act.getQueue().isEmpty())
                process();
        }while(!act.isInterrupted());
    }
}
