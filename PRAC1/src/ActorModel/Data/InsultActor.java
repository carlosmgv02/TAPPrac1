package ActorModel.Data;

import ActorModel.Data.Messages.Insult.*;
import ActorModel.Data.Messages.Message;

import java.util.*;

/*
Preguntes a Pedro:
com ficar send i recieve als missatges
si els insult message implementen o hereten de im i de mess
 */


public class InsultActor extends Actor {

    protected List<String> insultList = new ArrayList<>(Arrays.asList("tonto", "feo", "inútil", "gilipollas"));

    @Override
    public void process(){
        Message msg=cua.poll();
        switch(msg){
            case GetInsultMessage m1 -> {
                Collections.shuffle(insultList);
                if (msgIsValid(m1))
                    cua.offer(new Message(m1.getFrom(), insultList.get(0)));
            }
            case GetAllInsultsMessage m3 -> {
                Collections.shuffle(insultList);
                //TODO: cambiarlo para que no se envie el mensaje dos veces a la cola
                insultList.forEach(e -> {
                    Message temp = new Message(msg.getFrom(), e);
                    if (msgIsValid(temp))
                        cua.offer(temp);
                });
            }
            case AddInsultMessage m4 -> {
                String insult = msg.getText();
                insultList.add(insult);
                //insultList.add(InsultGenerator.getRandomInsult());
            }
            case null -> {}
            default -> {
                if (msgIsValid(msg))
                    cua.offer(msg);
            }
        }
    }

    private boolean msgIsValid(Message m) {
        return !cua.contains(m);
    }



    @Override
    public Queue<Message> getQueue() {
        //return null;
        return cua;
    }


    private static class InsultGenerator {
        private static final List<String> insultos = new ArrayList<>(Arrays.asList("cabron", "gusano", "soplapollas", "anacleto", "betta", "nobita", "pringao", "capullo", "estúpido"));

        public static String getRandomInsult() {
            return insultos.get((int) (Math.random() * insultos.size()));
        }
    }
}
