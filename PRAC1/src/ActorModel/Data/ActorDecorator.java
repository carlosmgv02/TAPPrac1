package ActorModel.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ActorDecorator implements Actor {

    // tenemos que crear un decorator para poder modificar el procesado de mensajes a un actor
    // para eso crearemos un FirewallDecorator y un EncryptionDecorator

    // el FirewallDecorator solo nos dejará pasar los mensajes los cuáles el emisor sea un actor registrado en ActorContext
    // detendrá todos los mensajes que provienen de un proxy

    // tenemos que crear un LambdaFirewallDecorator que nos permita pasar mensajes que cumplan una condición lambda
    // usando un AddClosureMessage

    // el EncryptionDecorator encriptará los mensajes y desencriptará en Actores interconectados

    private HashMap<String,Actor> actorSet=new HashMap<>();

    public void FirewallDecorator(Actor actor){
        if(actorSet.containsKey(actor)){    // si el actor está en el actorseSet
            actor.process();            // procesa el mensaje
                                        // no se si está bién pero por ahí deben ir los tiros
        }
    }
/*
    public void LambdaFirewallDecorator(Actor actor, String lambda){
        if (lambda(actor)){             // si el actor cumple la condición lambda
            actor.process();            // procesa el mensaje
        }
    }
    */

    public void EncryptionDecorator(Actor actor){ //falta hacerlo
        // encriptar mensaje
        actor.process(); // procesa el mensaje
        // desencriptar mensaje
    }



   private Queue<Message> cua = new LinkedList<>();


    @Override
    public void send(Message msg) {
        cua.offer(msg);
    }

    @Override
    public Message process() {
        Message processedMessage;
        processedMessage = cua.element();
        cua.poll();

        return processedMessage;
    }

    @Override
    public int getQueLength() {
        return cua.toArray().length;
    }

    @Override
    public Queue getQue() {
        return this.cua;
    }
}
