package modelo.solicitud;

public class Solicitud {
    public int cod, solicitante, cuotas, estado;
    public double monto, taza;
    public String observaciones, fecha;

    public Solicitud(int cod, int solicitante, int cuotas, int estado, double monto, double taza, String observaciones, String fecha) {
        this.cod = cod;
        this.solicitante = solicitante;
        this.cuotas = cuotas;
        this.estado = estado;
        this.monto = monto;
        this.taza = taza;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }
}
