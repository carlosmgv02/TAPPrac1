package ActorModel.Data;


import java.util.Queue;
import java.util.List;
import java.util.ArrayList;


public class InsultService extends InsultActor {
    private final String id;
    private Thread t;
    private Actor actor;

    private List<Message> insultlist = new ArrayList<>();

    public void addInsult(String insult){
        Message msg = new Message(insult);
        addInsultMessage(msg);
    }

    public Message getInsult(){
        return insultlist.get((int)(Math.random()* insultlist.size()));
    }
    public InsultService(String id, Actor actor) {
        super(id);
        this.id = id;
        this.actor = actor;
    }

    @Override
    public void send(Message msg) {
        actor.send(msg);
    }

    @Override
    public Message process() {
        return actor.process();
    }

    @Override
    public int getQueLength() {
        return actor.getQueLength();
    }

    @Override
    public void run() {
        actor.run();
    }

    @Override
    public void start() {
        actor.start();
    }

    @Override
    public Queue<Message> getQue() {
        return actor.getQue();
    }
}
}
