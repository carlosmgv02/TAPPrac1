public class CheckingAccount extends CompteBancari{
    public CheckingAccount(double saldo,Client client){
        super(saldo,client);
        interes=0.1;
    }
    public void revision(){
        saldo=saldo+saldo*getInteres();
    }
}
