package DesignPatterns.Decorator;

public class Main {
    public static void main(String[] args) {
        Automovil toyota=new AutoElectrico(new Toyota("Toyota"));
        toyota.start();
        toyota.acelerar();
        toyota.frenar();
    }
}
