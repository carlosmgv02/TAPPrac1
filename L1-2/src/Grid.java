import java.util.*;
public class Grid {
    private List<Procesador>procesadores;
    private List<Tarea>tareas;

    public Grid(){
        procesadores=new LinkedList<Procesador>();
        tareas=new LinkedList<Tarea>();
    }
    public void addTarea(Tarea task){
        tareas.add(task);
    }
    public void addProcesador(Procesador procesador){
        procesadores.add(procesador);
    }
    public List<Tarea>getTareas(){
        return tareas;
    }
    public void resolverTareas(){
        for (Procesador procesador : procesadores) {
            procesador.resolverTarea();
        }
    }

}
