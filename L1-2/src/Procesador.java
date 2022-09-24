public class Procesador {
    private String id;
    private String ip;
    private int capacitat;
    private int ram;
    private int storage;
    public boolean busy;
    private Tarea tarea;


    public Tarea getTarea() {
        return tarea;
    }
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
    public void resolverTarea(){
        tarea.operacion();

    }

}
