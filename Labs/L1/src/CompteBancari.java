public class CompteBancari {
    protected double saldo;
    private Client propietari;
    protected double interes;

    public CompteBancari(double saldo,Client propietari){
        this.saldo = saldo;
        this.propietari = propietari;
    }
    public void setInteres(double interes) {
        this.interes = interes;
    }
    public void depositar(double importe){
        saldo+=importe;
    }
    public void retirar(double importe)throws SinSaldo{
        if(importe>saldo)
            throw new SinSaldo("No hay suficiente saldo");
        else saldo-=importe;
    }
    public double getSaldo(){
        return saldo;
    }
    public void setPropietari(Client propietari){
        this.propietari = propietari;
    }
    public double getInteres(){
        return interes;
    }
    public double getComision(){
        return 1;
    }
    public void monthlyRevision(){
        saldo=saldo+saldo*getInteres()-getComision();
    }

    @Override
    public String toString(){
        return "CompteBancari{"+"saldo="+saldo+", propietari="+propietari+"}";
    }
}
