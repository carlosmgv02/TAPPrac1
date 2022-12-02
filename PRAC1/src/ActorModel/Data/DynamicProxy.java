package ActorModel.Data;

import ActorModel.Data.Messages.Message;

public class DynamicProxy extends Actor {
    private final String id;
    private final Actor actor;
    private Thread t;

    public DynamicProxy(String id, Actor actor) {
        this.id = id;
        this.actor = actor;
    }


    @Override
    public Message process() {
        return null;
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


}

