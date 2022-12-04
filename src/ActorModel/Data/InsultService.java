package ActorModel.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InsultService extends InsultActor {

    private InsultActor ia;
    //For example, an
    //InsultService class with methods addInsult,
    // getAllInsults, getInsult.

    public InsultService(){
        super();

    }

    public void addInsult(String insult){
        this.ia.insultList.add(insult);
    }

    public String getInsult(){
        return this.ia.insultList.get(0);
    }
    public List getAllInsults(){
        List a = new ArrayList<String>(this.ia.insultList);
        return a;
    };


}
