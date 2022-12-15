package ActorModel.Factory;

public class VirtualContextFactory implements AbstractContextFactory {

    public AbstractContext create() {
        return new VirtualContext();
    }
}

