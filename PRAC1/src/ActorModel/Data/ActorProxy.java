package ActorModel.Data;

import java.util.LinkedList;
import java.util.Queue;


public class ActorProxy implements Actor,Runnable{
    //No sabem ben be que fa pero la classe esà creada
    private final String id;
    private Thread t;//seguramente tengamos que cambiar el thread a insultActor,RingActor, etc.
    private Actor a;
    private Queue<Message> cua = new LinkedList<>();
    public ActorProxy(Actor act,String id){
        this.a=act;
        this.id=id;
        start();
    }

    //Each actor has a queue 4 the messages

    //Method 2 send a message to the actor
    @Override
    public void send(Message msg) {
        //Inserts the specified element into the queue
        a.send(msg);

    }

    //Method that processes the message and deletes it from the queue
    @Override
    public Message process() {
        return a.process();
    }
    @Override
    public int getQueLength(){
        return cua.toArray().length;
    }
    @Override

    public synchronized Queue<Message> getQueue(){
        return this.cua;
    }

    /**
     * Method that stops the actor from running
     */
    public void quitMessage(){
        //stop thread from running
        t.interrupt();
    }



    public void run() {
        //try{
        if(a.getQueLength()!=0)
            while(!a.getQueue().isEmpty()){
                a.process();
                //Thread.sleep(50);
            }
       /* }catch(InterruptedException e){
            System.out.println("Thread "+id+" interrupted.");
        }*/
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