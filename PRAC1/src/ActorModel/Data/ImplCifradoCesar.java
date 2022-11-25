package ActorModel.Data;



public interface ImplCifradoCesar {
    public static final String alfabeto = "abcdefghijklmnopqrstuvwxyz";
    public String cifrar(String mensaje,int clave);
    public String descifrar(String mensaje,int clave);
}
