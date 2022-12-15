package ActorModel.Factory;

public class PlatformContextFactory implements AbstractContextFactory {

    @Override
    public AbstractContext create() {
        return new PlatformContext();
    }
}

