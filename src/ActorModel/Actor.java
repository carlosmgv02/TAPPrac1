package ActorModel;

import ActorModel.Messages.Message;
import ActorModel.Messages.QuitMessage;

import ActorModel.Observer.MonitorService;
import ActorModel.Observer.Observable;
import ActorModel.Observer.Observer;
import ActorModel.Observer.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class that represents an actor.
 * <p>
 * We will use this class to establish communication between actors and simulate the queue message sending.
 * Each actor will have a queue and actors who want to communicate with it, will leave messages in its queue.
 * </p>
 */

public class Actor implements Runnable, Observable {
    //Thread implementation has been moved from Thread extension to Runnable implementation
    //This is because we want to be able to use the same thread for multiple actors, E.G. the same thread for the Encryption and the actor

    /**
     * Queue in which the messages sent from other actors will be stored.
     */
    protected Queue<Message> cua = new LinkedBlockingQueue<>();
    protected Collection<Observer> observers=new ArrayList<>(); //TODO PREGUNTAR SI FA FALTA TENIR AQUESTA VARIABLE
    private Status status= Status.CREATED; //TODO PREGUNTAR COM CANVIAR ELS ESTATS DE CADA ACTOR (ACTOR, ACTORCONTEXT...)

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
     * <p>
     * Message processing will automatically stop if a message hasn't been received in 2 seconds.<br>
     * Message processing won't stop if there are still messages in the queue.
     * </p>
     */
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end = start + 2 * 1000; // 2 seconds * 1000 ms/sec
        boolean as=false;
        long aux=start;
        do {
            if (!cua.isEmpty()) {
                try {
                    Thread.sleep(0);
                    process();
                } catch (InterruptedException e) {
                    System.out.println("Actor has been interrupted");
                }
                as=false;
            }
            else{
                if(!as){
                    start= System.currentTimeMillis();
                    end= start + 2 * 1000;
                    as=true;
                }
            }
        } while (System.currentTimeMillis()< end || !as);
        //} while (true);
    }

    /**
     * Method that processes the message
     * @return the processed message
     */
    public Message process(){
        Message msg = cua.poll();
        switch (msg){
            case QuitMessage m1->{
                Thread.currentThread().interrupt();
            }
            default->{
                return msg;
            }
            case null->{
                return null;
            }
        }

        return msg;

    }

    /**
     * Method that adds a message to the queue
     *
     * @param m the message to add
     */
    public synchronized void offer(Message m) {
        this.cua.offer(m);
    }


    @Override
    public void attach(Observer o) {
        MonitorService.attach(this,o);

    }

    @Override
    public void detach(Observer o) {
        MonitorService.detach(this,o);
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Status status) {
        for(Observer observer : observers){
            observer.update(status);
        }

    }
}
