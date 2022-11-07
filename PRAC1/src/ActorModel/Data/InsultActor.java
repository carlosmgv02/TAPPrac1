package ActorModel.Data;

import ActorModel.Data.Messages.Insult.*;
import ActorModel.Data.Messages.Message;

import java.util.*;

public class InsultActor extends Actor{


    private List<Message> insultList = new ArrayList<>();


    @Override
    public void send(Message msg){


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

    @Override
    public Queue<Message> getQueue() {
        return null;
    }


}