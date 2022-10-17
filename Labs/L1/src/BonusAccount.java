public class BonusAccount extends CompteBancari{
    public BonusAccount(double saldo,Client client){
        super(saldo,client);
        interes=0.6;
    }
}
