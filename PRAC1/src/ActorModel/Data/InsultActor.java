package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class InsultActor extends Actor{

    private List<Message> insultList = new ArrayList<>();



    /**
     * Returns a random message from the insult list
     * @return random message
     */
    public Message getInsultMessage(){
        return insultList.get((int)(Math.random()* insultList.size())); //devolvemos un insulto random de la lista
    }

    /**
     * Adds a new message to the insultList
     * @param msg message we want to add
     */
    public void addInsultMessage (Message msg){
        insultList.add(msg);    //añadimos un insulto a la lista
    }

    /**
     * Returns the insult list
     * @return
     */
    public List<Message> getAllMessages (){
        return insultList;  //devolvemos la lista de insultos
    }

    @Override
    public void send(Message msg) {
        insultList.add(msg);
    }

    @Override
    public Message process() {
            insultList.forEach(i -> {
                System.out.println(i);
            });
            insultList.clear();
            interrupt();
        return null;
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    @Override
    public Queue<Message> getQueue() {
        return null;
    }
}