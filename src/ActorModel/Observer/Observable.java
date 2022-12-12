package ActorModel.Observer;


public interface Observable {
    void attach(Observer o);

    void detach(Observer o);

    void notifyObservers(Status status);

}
