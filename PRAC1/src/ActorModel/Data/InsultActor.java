package ActorModel.Data;

import ActorModel.Data.Messages.Insult.*;

import java.util.*;

/*
Preguntes a Pedro:
com ficar send i recieve als missatges
si els insult message implementen o hereten de im i de mess
 */

public class InsultActor extends Actor{

    @Override
    public void send(String msg){


        switch (msg){
            case GetInsultMessage m1-> {
                //prova de funcionament
                System.out.println(m1.getRandomInsult());
            }
            //case AddInsultMessage m2-> insultList.add(m2);
            case GetAllInsultsMessage m3-> interrupt();

            default -> cua.offer(msg);

        }
        //get random element from insultList

    }

    @Override
    public String process() {
        for(String msg:cua) {
            System.out.println(msg);
        }
        cua.clear();
        return null;
    }


    @Override
    public Queue<String> getQueue() {
        //return null;
        return cua;
    }


}