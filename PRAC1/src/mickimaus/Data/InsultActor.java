package mickimaus.Data;

import java.util.ArrayList;
import java.util.List;
public class InsultActor extends ActorProxy{

    private List<Message> insultList = new ArrayList<>();

    public InsultActor(String id) {
        super(id);

    }

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
}
