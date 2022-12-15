package ActorModel.Factory;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;

public class PlatformContext extends ActorContext implements AbstractContext {

    public ActorProxy spawnActor(String name, Actor type) {
        ActorProxy newActor = new ActorProxy(type, name);
        //new Thread(newActor).start();
        actorSet.put(name, type);
        /**
         * Thread implementation has been moved from Thread extension to Runnable implementation
         */
        Thread t = new Thread(type);
//        Thread t = Thread.startVirtualThread(type); //We now create the thread manually and pass the Runnable object
        threadMap.put(type, t); //We temporarily store the thread to keep track of its behaviour
        t.start(); //UNCOMMENT THIS LINE TO START THE THREAD AUTOMATICALLY


        return newActor;
    }
}
