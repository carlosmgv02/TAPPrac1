package ActorModel.Data;

import ActorModel.Data.Messages.Insult.*;
import ActorModel.Data.Messages.Message;

import java.util.*;

/*
Preguntes a Pedro:
com ficar send i recieve als missatges
si els insult message implementen o hereten de im i de mess
 */

public class InsultActor extends Actor{

    protected List<String> insultList=new ArrayList<>(Arrays.asList("tonto","feo","inútil","gilipollas"));


    public void send(Message msg){

        switch (msg){
            case GetInsultMessage m1-> {
                Collections.shuffle(insultList);
                cua.offer(new Message(msg.getFrom(),insultList.get(0)));
            }
            case GetAllInsultsMessage m3->  {
                Collections.shuffle(insultList);
                //TODO: cambiarlo para que no se envie el mensaje dos veces a la cola
                insultList.forEach(e->cua.offer(new Message(msg.getFrom(), e)));
            }
            case AddInsultMessage m4-> {
                String insult = msg.getText();
                insultList.add(insult);
//                insultList.add(InsultGenerator.getRandomInsult());
            }

            default -> cua.offer(msg);

        }
        //get random element from insultList

    }

    @Override
    public Message process() {
        for(Message msg:cua) {
            System.out.println(msg);
        }
        cua.clear();
        return null;
    }


    @Override
    public Queue<Message> getQueue() {
        //return null;
        return cua;
    }
    private class InsultGenerator{
        private static final List<String> insultos=new ArrayList<>(Arrays.asList("cabron", "gusano", "soplapollas","anacleto", "betta", "nobita", "pringao","capullo","estúpido"));
        public static String getRandomInsult(){
            return insultos.get((int)(Math.random()*insultos.size()));
        }
    }

}
