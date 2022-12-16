package ActorModel.Factory;

/**
 * Class used for creating a Virtual Context using the Factory Pattern
 */
public class VirtualContextFactory implements AbstractContextFactory {

    public AbstractContext create() {
        return new VirtualContext();
    }
}

