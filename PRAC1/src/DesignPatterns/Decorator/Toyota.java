package DesignPatterns.Decorator;

public class Toyota implements Automovil{
    private String nombre;
    public Toyota(String s){
        nombre=s;
    }
    @Override
    public void acelerar() {
        System.out.println("Toyota acelerando");
    }
    @Override
    public void frenar() {
        System.out.println("Toyota frenando");
    }
    @Override
    public void start() {
        System.out.println("Toyota encendido");
    }
}
