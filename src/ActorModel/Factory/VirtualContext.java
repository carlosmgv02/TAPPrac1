package ActorModel.Factory;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;

public class VirtualContext extends ActorContext implements AbstractContext {
    @Override
    public ActorProxy spawnActor(String name, Actor type) {
        ActorProxy newActor = new ActorProxy(type, name);
        //new Thread(newActor).start();
        actorSet.put(name, type);
        /**
         * Thread implementation has been moved from Thread extension to Runnable implementation
         */

        Thread t = Thread.startVirtualThread(type);


//        Thread t = Thread.startVirtualThread(type); //We now create the thread manually and pass the Runnable object
        threadMap.put(type, t); //We temporarily store the thread to keep track of its behaviour
        return newActor;
    }
}
