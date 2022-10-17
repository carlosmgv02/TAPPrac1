public abstract class Monstrugo {
    private String nom;
    private int vitalitat;
    private int força;
    private int resistencia;

    public Monstrugo(String nom, int força,int resistencia){
        this.nom=nom;
        this.força=força;
        this.resistencia=resistencia;
        vitalitat=10;
    }
    public abstract void atacar(Monstrugo enemy);
    public void perderPuntosVitalidad(int puntos){
        vitalitat-=puntos;
    }
    public void perderPuntosFuerza(int puntos){
        força-=puntos;
    }
    public void perderPuntosResistencia(int puntos){
        resistencia-=puntos;
    }
    public boolean derrotado(){
        if(vitalitat<=0||força<=0||resistencia<=0) {
            return true;
        }
        else{
            return false;
        }
    }
    //getters & setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getVitalitat() {
        return vitalitat;
    }
    public void setVitalitat(int vitalitat) {
        this.vitalitat = vitalitat;
    }
    public int getForça() {
        return força;
    }
    public void setForça(int força) {
        this.força = força;
    }
    public int getResistencia() {
        return resistencia;
    }
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
    public String toString(){
        return (vitalitat+" punts de vitalitat, "+força+" punts de força, "+resistencia+" punts de resistencia");
    }
}
