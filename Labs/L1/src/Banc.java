import java.util.LinkedList;
import java.util.List;

public class Banc {
    private String name;
    private List<CompteBancari> comptes=new LinkedList<CompteBancari>();
    private String[]llista=new  String[4];
    private List<Client>clients=new LinkedList<Client>();
    public Banc(String name){
        this.name=name;

    }
    public void addCompte(CompteBancari nouCompte){
        comptes.add(nouCompte);
    }
    public void addClient(Client c){
        clients.add(c);
    }
    public void showComptes(){
        for(CompteBancari c: comptes){
            System.out.println(c);
        }
    }
    public void revision(){
        for(CompteBancari c:comptes){
            c.monthlyRevision();
        }
    }
    public List<Client> getClients(){
        return clients;
    }
    public List<CompteBancari> getComptes(){
        return comptes;
    }

    public static Client pickRandomClient(Banc b1){
        int random=(int)(Math.random()*b1.getClients().size());
        return b1.getClients().get(random);
    }
    //method to pick a random CompteBancari from a Banc
    public CompteBancari pickRandomCompteBancari(Banc b1){
        int random=(int)(Math.random()*b1.getComptes().size());
        return b1.getComptes().get(random);
    }
    public void assignPrize(){
        CompteBancari winner=pickRandomCompteBancari(this);
        winner.depositar(300);
    }
}
