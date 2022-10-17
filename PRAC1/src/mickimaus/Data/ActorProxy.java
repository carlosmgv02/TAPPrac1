package mickimaus.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ActorProxy implements Actor,Runnable{
    //No savem ben be que fa pero la classe esà creada
    private String id;
    private Thread t;
    public ActorProxy (String id){
        this.id = id;
    }


    //Each actor has a queue 4 the messages
    private Queue<Message> cua = new LinkedList<>();
    //private BlockingQueue<Message> cua2=new SynchronousQueue<>();

    //Method 2 send a message to the actor
    @Override
    public void send(Message msg) {
        //Inserts the specified element into the queue
        cua.offer(msg);

    }

    //Method that processes the message and deletes it from the queue
    @Override
    public Message process() {
        //- Returns the head of the queue.
        Message processedMessage=null;

            processedMessage = cua.element();
            System.out.println(id+" processed: "+processedMessage.getText());
            //- Deletes the head of the queue.
            cua.poll();



        return processedMessage;

    }
    @Override
    public int getQueLength(){
        return cua.toArray().length;
    }
    @Override

    public synchronized Queue<Message> getQue(){
        return this.cua;
    }
    public void quitMessage(){

    }


    public void run() {
        try{
            while(!cua.isEmpty()){
                process();
                Thread.sleep(50);
            }
        }catch(InterruptedException e){
            System.out.println("Thread "+id+" interrupted.");
        }
        //while(true)

    }
    public void start(){
        System.out.println("Starting "+id);
        if(t==null){
            t=new Thread(this,id);
            t.start();
        }
    }
    public void stop(){
        Thread.currentThread().interrupt();
    }
    @Override
    public synchronized String toString(){
        return id;
    }
}
