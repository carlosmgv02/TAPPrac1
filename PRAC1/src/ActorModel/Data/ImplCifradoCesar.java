package ActorModel.Data;



public interface ImplCifradoCesar {
    public static final String alfabeto = "abcdefghijklmnopqrstuvwxyz";
    public String cifrar(String mensaje);
    public String descifrar(String mensaje);
}
