package DesignPatterns.Decorator;

public class AutoElectrico extends AutomovilDecorador{
    public AutoElectrico(Automovil a) {
        super(a);
    }
    @Override
    public void acelerar() {
        getAutomovil().acelerar();
        System.out.println("Auto electrico acelerando");
    }
    @Override
    public void frenar() {
        getAutomovil().frenar();
        System.out.println("Auto electrico frenando");
    }
    @Override
    public void start() {
        getAutomovil().start();
        System.out.println("Auto electrico encendido");
    }
}
