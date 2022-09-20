public class Client {
    private String id;
    private String nom;
    private String adreca;
    private String telefon;


    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Client(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
