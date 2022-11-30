package ActorModel.Data;

import ActorModel.Data.Messages.Insult.*;
import ActorModel.Data.Messages.Message;
import jdk.swing.interop.SwingInterOpUtils;

import java.lang.reflect.Array;
import java.util.*;

/*
Preguntes a Pedro:
com ficar send i recieve als missatges
si els insult message implementen o hereten de im i de mess
 */


public class InsultActor extends Actor {

    protected List<String> insultList = new ArrayList<>(Arrays.asList("tonto", "feo", "inútil", "gilipollas"));

    @Override
    public synchronized Message process(){
        Message msg=cua.poll();
        switch(msg){
            case GetInsultMessage m1 -> {
                Collections.shuffle(insultList);
                if (msgIsValid(m1)){
                    System.out.println("GETINSULT: "+insultList.get(0));
                    System.out.println("\tFrom: "+this);
                    System.out.println("\tTo: "+ActorContext.lookupProxy(this).getProxyId());
//                    System.out.println("GETINSULT *"+insultList.get(0)+"* *"+ActorContext.lookupProxy(this).getProxyId()+"*");
                    ActorContext.lookupProxy(this).offer(new Message(this,insultList.get(0)));
                    //System.out.println(insultList.get(0));
                    //cua.offer(new Message(m1.getFrom(), insultList.get(0)));
                    return new Message(this,insultList.get(0));
                }
            }
            case GetAllInsultsMessage m3 -> {
                //Collections.shuffle(insultList);
                //TODO: cambiarlo para que no se envie el mensaje dos veces a la cola
                System.out.println("GETALLINSULTS: "+ Arrays.asList(insultList));
                System.out.println("\tFrom: "+this);
                System.out.println("\tTo: "+ActorContext.lookupProxy(this).getProxyId());
                insultList.forEach(e -> {
                    Message temp = new Message(this, e);
//                    System.out.println("GETALLINSULTS SENT FROM INSULT ACTOR, TO: *"+ActorContext.lookupProxy(this).getProxyId()+"*");
                    ActorContext.lookupProxy(this).offer(temp);
                    //System.out.println(temp);
                    /*
                    if (msgIsValid(temp))
                        cua.offer(temp);
                    */
                });
                return new Message(this,Arrays.asList(insultList).toString());
            }
            case AddInsultMessage m4 -> {
                String insult = msg.getText();
                System.out.println("ADDINSULT: "+insult);
                System.out.println("\tFrom: "+this);
                System.out.println("\tTo: "+this+".insultList");
                if(!insultList.contains(msg.getText()))
                    insultList.add(insult);
                //System.out.println(Arrays.asList(insultList));
                //insultList.add(InsultGenerator.getRandomInsult());
                return new Message(this,insult);
            }
            case null -> {}
            default -> {
                System.out.println(msg);
                /*if (msgIsValid(msg))
                    cua.offer(msg);*/
            }
        }
        return null;
    }

    private boolean msgIsValid(Message m) {
        return !cua.contains(m);
    }



    @Override
    public Queue<Message> getQueue() {
        //return null;
        return cua;
    }
    public List<String> getInsultList(){
        return insultList;
    }


    private static class InsultGenerator {
        private static final List<String> insultos = new ArrayList<>(Arrays.asList("cabron", "gusano", "soplapollas", "anacleto", "betta", "nobita", "pringao", "capullo", "estúpido"));

        public static String getRandomInsult() {
            return insultos.get((int) (Math.random() * insultos.size()));
        }
    }
}
