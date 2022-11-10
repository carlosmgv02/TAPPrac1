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


    protected List<InsultMessage> insultList = new ArrayList<>();



    public void send(InsultMessage msg){


        switch (msg){
            case GetInsultMessage m1-> {
                ArrayList<Message> temp=new ArrayList<>(insultList);
                Collections.shuffle(temp);
                Message aux=temp.get(0);
            }
            case AddInsultMessage m2-> insultList.add(msg);
            case GetAllInsultsMessage m3-> interrupt();

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

    public Message receive(){
        return null;
    }
    @Override
    public Queue<Message> getQueue() {
        return null;
    }


}