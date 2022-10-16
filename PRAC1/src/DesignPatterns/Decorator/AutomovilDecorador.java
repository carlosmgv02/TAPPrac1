package DesignPatterns.Decorator;

public abstract class AutomovilDecorador implements Automovil {
    private Automovil automovil;
    public AutomovilDecorador(Automovil a){
        automovil=a;
    }
    protected Automovil getAutomovil(){
        return automovil;
    }

}
