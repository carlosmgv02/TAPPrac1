package ActorModel;

import ActorModel.Messages.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class that represents an actor.
 * <p>
 * We will use this class to establish communication between actors and simulate the queue message sending.
 * Each actor will have a queue and actors who want to communicate with it, will leave messages in its queue.
 * </p>
 */

public abstract class Actor implements Runnable {
    //Thread implementation has been moved from Thread extension to Runnable implementation
    //This is because we want to be able to use the same thread for multiple actors, E.G. the same thread for the Encryption and the actor

    /**
     * Queue in which the messages sent from other actors will be stored.
     */
    protected Queue<Message> cua = new LinkedBlockingQueue<>();


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
     * Method that defines the behaviour of the actor
     */
    @Override
    public void run() {
        do {
            if (!cua.isEmpty()) {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                process();
            }

        } while (true);
    }

    /**
     * Method that processes the message
     *
     * @return the processed message
     */
    public abstract Message process();

    /**
     * Method that adds a message to the queue
     *
     * @param m the message to add
     */
    protected synchronized void offer(Message m) {
        this.cua.offer(m);
    }

}
