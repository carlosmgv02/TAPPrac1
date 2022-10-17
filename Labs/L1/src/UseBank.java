public class UseBank {
    public static void main(String[] args) {
        Client c1=new Client("1","Carlos");
        Client c2=new Client("2","Nil");
        Client c3=new Client("3","Genis");

        CompteBancari ac1=new CheckingAccount(90,c1);
        CompteBancari ac2=new SaveAccount(100,c2);
        CompteBancari ac3=new InvestFund(10,c3);
        CompteBancari ac4=new BonusAccount(10,c3);

        Banc b1=new Banc("CAIXABANK");

        b1.addCompte(ac1);
        b1.addCompte(ac2);
        b1.addCompte(ac3);
        b1.addCompte(ac4);

        b1.addClient(c1);
        b1.addClient(c2);
        b1.addClient(c3);

        b1.showComptes();

        ac1.depositar(125);
        System.out.println();
        b1.showComptes();
        int importe=10;
        try{
            ac4.retirar(importe);
            System.out.println("Has tret "+importe+" euros");
            System.out.println("Saldo actual: "+ac4.getSaldo());
        }catch(SinSaldo e1){
            System.out.println(e1.getMessage());
        }
        b1.assignPrize();
        b1.showComptes();

    }






}
