package ActorModel.Factory;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Observer.MonitorService;
import ActorModel.Observer.Status;

/**
 * Class used to create an ActorContext using Virtual Threads
 * <p>
 *     It is a factory that creates an ActorContext using Virtual Threads
 * </p>
 * @see ActorContext ActorContext
 * @see AbstractContextFactory AbstractContextFactory
 * @see VirtualContextFactory VirtualContextFactory
 */
public class VirtualContext extends ActorContext implements AbstractContext {
    /**
     * Method used to Spawn an Actor into the VirtualContext
     * @param name the name of the actor, used to index it in the Map
     * @param type the type of the actor
     * @return the ActorProxy that controls the actor
     * @see ActorProxy ActorProxy
     */
    @Override
    public ActorProxy spawnActor(String name, Actor type) {
        ActorProxy newActor = new ActorProxy(type, name);
        actorSet.put(name, type);

        Thread t = Thread.startVirtualThread(()->{
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
        });
        threadMap.put(type, t);
        return newActor;
    }
}
