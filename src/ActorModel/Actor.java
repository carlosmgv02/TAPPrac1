package ActorModel;

import ActorModel.Messages.Message;
import ActorModel.Messages.QuitMessage;
import ActorModel.Observer.MonitorService;
import ActorModel.Observer.Observer;
import ActorModel.Observer.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class that represents an actor.
 * <p>
 * We will use this class to establish communication between actors and simulate the queue message sending.
 * Each actor will have a queue and actors who want to communicate with it, will leave messages in its queue.
 * </p>
 */

public class Actor {
    /**
     * Thread implementation has been moved from Thread extension to Runnable implementation
     * This is because we want to be able to use the same thread for multiple actors, E.G. the same thread for the Encryption and the actor
     */
    private final Status status = Status.CREATED;
    /**
     * Queue in which the messages sent from other actors will be stored.
     */
    protected Queue<Message> cua = new LinkedBlockingQueue<>();
    protected Collection<Observer> observers = new ArrayList<>();

    /**
     * Returns the length of the queue
     *
     * @return the length of the queue
     */
    public int getQueLength() {
        return cua.size();
    }

    /**
     * Returns the queue of the actor
     *
     * @return the queue of the actor
     */
    public Queue<Message> getQueue() {
        return cua;
    }



    /**
     * Method that processes the message
     *
     * @return the processed message
     */
    public Message process() throws InterruptedException {
        Message msg = cua.poll();
        switch (msg) {
            case QuitMessage m1 -> {
                throw new InterruptedException();
            }
            default -> {
                return msg;
            }
            case null -> {
                return null;
            }
        }
    }


    /**
     * Method that adds a message to the queue
     *
     * @param m the message to add
     */
    public synchronized void offer(Message m) {
        this.cua.offer(m);

        ActorProxy ap = m.getFrom();

        MonitorService.addReceivedMessage(ap != null ? ap.getActor() : null, m);
        MonitorService.setTraffic(this);

        MonitorService.setStatus(this, Status.MESSAGE);
    }


}
