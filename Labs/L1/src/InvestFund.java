public class InvestFund extends CompteBancari{
    public InvestFund(double saldo,Client client){
        super(saldo,client);
        interes=0.34;
    }
    @Override
    public void retirar(double amount)throws SinSaldo{
        throw new SinSaldo("No es poden treure diners");
    }


}
