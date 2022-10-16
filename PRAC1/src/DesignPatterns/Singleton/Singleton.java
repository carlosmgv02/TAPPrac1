package DesignPatterns.Singleton;

public class Singleton {
    // Static variable reference of single_instance
    // of type aplicacion.Singleton
    private static Singleton single_instance = null;

    // Declaring a variable of type String
    public String s;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Singleton()
    {
        s = "Hello I am a string part of aplicacion.Singleton class";
    }

    // Static method 
    // Static method to create instance of aplicacion.Singleton class
    public synchronized static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }
}
