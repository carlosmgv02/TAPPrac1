package ActorModel.Factory;

import ActorModel.Actor;
import ActorModel.ActorProxy;

public interface AbstractContext {
    ActorProxy spawnActor(String actorName, Actor type);
}
