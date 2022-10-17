import java.util.List;

public class main {
    public static void main(String[] args) {
        Grid grid=new Grid();
        Procesador pr1=new Procesador();
        Procesador pr2=new Procesador();

        int nums[]={6,5,4,3,2,1};
        Suma suma=new Suma(nums,"suma1");
        Producto prod=new Producto(nums,"prod1");

        pr1.setTarea(suma);
        pr2.setTarea(prod);

        grid.addProcesador(pr1);
        grid.addProcesador(pr2);

        grid.addTarea(suma);
        grid.addTarea(prod);

        grid.resolverTareas();

        List<Tarea> resultados = grid.getTareas();

        for (Tarea t:resultados)
            System.out.println(t);

    }
}
