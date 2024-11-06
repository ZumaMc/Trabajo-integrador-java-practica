import java.util.ArrayList;
import java.util.List;
public class Empleado {
    String nombreEmpleado;
    long cuil;
    int anioIngreso;
    double montoAntiguedad;
    double sueldoBasico;
    List<BonoSueldo> bonos;
    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public List<BonoSueldo> getBonos() {
        return bonos;
    }

    public void setBonos(List<BonoSueldo> bonos) {
        this.bonos = bonos;
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public double getMontoAntiguedad() {
        return montoAntiguedad;
    }

    public void setMontoAntiguedad(double montoAntiguedad) {
        this.montoAntiguedad = montoAntiguedad;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }
    public double calcularAntiguedad(){
        int antiguedad= 2024-getAnioIngreso();
        return sueldoBasico*0.02*antiguedad;
    }
}
