public class Suma extends Tarea{
    public Suma(int[]valores,String identificador){
        super(valores,identificador);
    }
    public void operacion(){
        resultado=0;
        for(int i=0;i<valores.length;i++){
            resultado+=valores[i];
        }
    }
}
