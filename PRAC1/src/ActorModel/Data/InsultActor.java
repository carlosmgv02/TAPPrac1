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

<<<<<<< Updated upstream
=======
    protected List<String> insultList = new ArrayList<>(Arrays.asList("tonto", "feo", "inútil", "gilipollas"));
>>>>>>> Stashed changes

    protected List<InsultMessage> insultList = new ArrayList<>();



    public void send(InsultMessage msg){

<<<<<<< Updated upstream

        switch (msg){
            case GetInsultMessage m1-> {
                ArrayList<Message> temp=new ArrayList<>(insultList);
                Collections.shuffle(temp);
                Message aux=temp.get(0);
=======
    public void send(Message msg) {

        switch (msg) {
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
//                insultList.add(InsultGenerator.getRandomInsult());
>>>>>>> Stashed changes
            }
            case AddInsultMessage m2-> insultList.add(msg);
            case GetAllInsultsMessage m3-> interrupt();

            default -> {
                if (msgIsValid(msg))
                    cua.offer(msg);
            }

        }
        //get random element from insultList

    }

    private boolean msgIsValid(Message m) {
        return !cua.contains(m);
    }

    @Override
    public Message process() {
        for (Message msg : cua) {
            System.out.println(msg);
        }
        cua.clear();
        return null;
    }

    public Message receive(){
        return null;
    }
    @Override
    public Queue<Message> getQueue() {
<<<<<<< Updated upstream
        return null;
=======
        //return null;
        return cua;
    }

    private class InsultGenerator {
        private static final List<String> insultos = new ArrayList<>(Arrays.asList("cabron", "gusano", "soplapollas", "anacleto", "betta", "nobita", "pringao", "capullo", "estúpido"));

        public static String getRandomInsult() {
            return insultos.get((int) (Math.random() * insultos.size()));
        }
>>>>>>> Stashed changes
    }


}