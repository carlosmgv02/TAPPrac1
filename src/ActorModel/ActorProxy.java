package ActorModel;

import ActorModel.Messages.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents an Actor Proxy, which controls the access to the actor
 *
 * <ul>
 *     <li>Send</li>
 *     <li>Receive</li>
 *     <li>Offer</li>
 *
 * </ul>
 *
 *
 */
public class ActorProxy {
    private final Actor actor;
    private final List<Message> receiveQueue;
    protected String id;


    public ActorProxy(Actor act, String id) {
        this.actor = act;
        this.id = id;
        receiveQueue = Collections.synchronizedList((new ArrayList<>()));
    }


    public List<Message> getProxyQueue() {
        return receiveQueue;
    }

    public void offer(Message m) {
        receiveQueue.add(m);
    }

    public void send(Message msg) {
        try {
            if (!Message.class.equals(msg.getClass())) {
                msg.setFrom(this);
            }
            this.actor.offer(msg);
        } catch (NullPointerException e) {
            System.out.println("ACTOR NOT FOUND");
        }
    }

    public String getProxyId() {
        return this.id;
    }

    public Actor getActor() {
        return this.actor;
    }


    public Message receive() {
        Message m;
        String txt = "\n\t";
        while (!receiveQueue.isEmpty()) {
            m = receiveQueue.get(0);
            txt += m.toString() + "\n\t";
            receiveQueue.remove(0);
        }
        return new Message(null, txt);
    }
}