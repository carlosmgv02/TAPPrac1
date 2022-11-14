package ActorModel.Data.Messages.Insult;

import ActorModel.Data.Actor;
import ActorModel.Data.Messages.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsultMessage extends Message {
    protected List<String> insultList=new ArrayList<>(Arrays.asList("tonto","feo","inútil","gilipollas"));


    public InsultMessage(Actor from, String message) {
        super(from, message);
    }

    public InsultMessage() {
    }
    protected void addInsult(String insult){
        this.insultList.add(insult);
    }
    public String getRandomInsult(){
        return this.insultList.get((int)(Math.random()*this.insultList.size()));
    }
}

