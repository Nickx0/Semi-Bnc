package modelo.profile;

public class Asociado {
    public int dni;
    public String token, nombre, apellido, telefono, contra;
    public double salario, saldo, aporte;

    public Asociado(int dni, String token, String nombre, String apellido, String telefono, String contra, double salario, double saldo, double aporte) {
        this.dni = dni;
        this.token = token;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.contra = contra;
        this.salario = salario;
        this.saldo = saldo;
        this.aporte = aporte;
    }

    public Asociado() {}
    
    
    
}
