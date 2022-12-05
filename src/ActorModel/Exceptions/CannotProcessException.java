package ActorModel.Exceptions;

public class CannotProcessException extends Exception {
    private static final long serialVersionUID = 1L;

    public CannotProcessException(String id){
        super("Actor with id: "+id+" cannot process the message");
    }
}

