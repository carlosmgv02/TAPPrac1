package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;

public class DynamicProxy extends Actor {
    private final String id;
    private Thread t;
    private Actor actor;

    public DynamicProxy(String id, Actor actor) {
        this.id = id;
        this.actor = actor;
    }

    @Override
    public Message receive() {
        return null;
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
    public Queue<Message> getQueue() {
        return actor.getQueue();
    }

}

