public abstract class Tarea {
    protected int valores[];
    protected int resultado;
    private String identificador;

    public Tarea(int[]valores,String identificador){
        this.valores=valores;
        this.identificador=identificador;
    }
    public abstract void operacion();
    public String toString(){
        return "("+identificador+","+resultado+")";
    }
}
