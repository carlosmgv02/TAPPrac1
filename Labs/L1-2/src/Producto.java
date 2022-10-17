public class Producto extends Tarea{
    public Producto(int[]valores,String identificador){
        super(valores,identificador);
    }
    public void operacion(){
        resultado=1;
        for(int val:valores){
            resultado*=val;
        }
    }
}
