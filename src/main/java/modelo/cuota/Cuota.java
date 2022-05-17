package modelo.cuota;

public class Cuota {
    public int codigo, ncuota, estado;
    public double capital, interes, neto;
    public String plazo;

    public Cuota(int codigo, int ncuota, int estado, double capital, double interes, double neto, String plazo) {
        this.codigo = codigo;
        this.ncuota = ncuota;
        this.estado = estado;
        this.capital = capital;
        this.interes = interes;
        this.neto = neto;
        this.plazo = plazo;
    }
}
