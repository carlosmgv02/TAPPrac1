package ActorModel.Factory;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Observer.MonitorService;
import ActorModel.Observer.Status;

/**
 * Platform Context class used to spawn Actors with platform Threads
 *
 * @see ActorContext ActorContext
 * @see AbstractContext AbstractContext
 */
public class PlatformContext extends ActorContext implements AbstractContext {

    /**
     * Method used to Spawn an Actor into the PlatformContext
     * @param name the name of the actor, used to index it in the Map
     * @param type the type of the actor
     * @return the ActorProxy that controls the actor
     * @see ActorProxy ActorProxy
     */
    public ActorProxy spawnActor(String name, Actor type) {
        ActorProxy newActor = new ActorProxy(type, name);
        actorSet.put(name, type);

        Runnable run = () -> {
            long start = System.currentTimeMillis();
            long end = start + 2 * 1000; // 2 seconds * 1000 ms/sec
            boolean as = false;

            do {
                if (!type.getQueue().isEmpty()) {

                    try {
                        type.process();
                        MonitorService.setTraffic(type);
                    } catch (InterruptedException e) {
                        System.out.println("Actor has been interrupted");
                        MonitorService.setStatus(type, Status.STOPPED);
                        break;
                    }
                    as = false;
                } else {
                    if (!as) {
                        start = System.currentTimeMillis();
                        end = start + 2 * 1000;
                        as = true;
                    }
                }
            } while (System.currentTimeMillis() < end || !as);
        };
        Thread t = new Thread(run);
        threadMap.put(type, t);
        t.start();
        return newActor;
    }
}
