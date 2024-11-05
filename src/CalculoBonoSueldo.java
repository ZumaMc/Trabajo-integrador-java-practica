import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CalculoBonoSueldo {
    String[][] haberes = {
            {"100", "Presentismo", "9"},
            {"101", "Titulo Profesional", "9"},
            {"102", "Horas Extraordinarias", "M"},
            {"103", "Horas Nocturnas", "M"},
            {"104", "Otros Haberes", "M"}
    };

    String[][] deducciones = {
            {"200", "Obra Social", "3"},
            {"201", "Jubilacion", "11"},
            {"202", "Sindicato", "2"},
            {"203", "Seguro", "1.5"},
            {"204", "Otros", "M"}
    };

    List<Integer> codigosIngresados = new ArrayList<>();

    public boolean verificarCodigo(int codigo) {
        if (codigosIngresados.contains(codigo)) {
            System.out.println("El código ya ha sido cargado, ingrese otro código.");
            return false;
        } else {
            codigosIngresados.add(codigo);
            System.out.println("Código ingresado correctamente.");
            return true;
        }
    }

    public String[] buscarItemEnHaberes(String codigo) {
        for (String[] item : haberes) {
            if (item[0].equals(codigo)) {
                return item;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empleado empleado = new Empleado();
        BonoSueldo bono = new BonoSueldo();
        CalculoBonoSueldo calculo = new CalculoBonoSueldo();
        System.out.println("Ingrese su nombre");
        empleado.nombreEmpleado = sc.nextLine();
        System.out.println("Ingrese su cuil");
        empleado.cuil = sc.nextLong();
        System.out.println("Ingrese su año de ingreso");
        while (true) {
            empleado.anioIngreso = sc.nextInt();
            if (empleado.anioIngreso > 2024)
                System.out.println("Ingrese un año valido");
            else
                break;
        }
        System.out.println("Ingrese su mes de liquidacion");
        int mesLiquidacion = sc.nextInt();
        bono.setMesLiquidacion(mesLiquidacion);
        System.out.println("Ingrese su año de liquidacion");
        int AnioLiquidacion;
        while (true) {
            AnioLiquidacion = sc.nextInt();
            if (AnioLiquidacion > 2024)
                System.out.println("Ingrese un año valido");
            else
                break;
        }
        bono.setAnioLiquidacion(AnioLiquidacion);
        System.out.println("Ingrese su sueldo basico");
        empleado.sueldoBasico = sc.nextInt();
        int montoantiguedad = (2024 - empleado.getAnioIngreso()) * 2;
        String bonoCalculado[][] = new String[10][4];
        bonoCalculado[0][0] = "Codigo Item";
        bonoCalculado[0][1] = "Denominacion";
        bonoCalculado[0][2] = "Haberes";
        bonoCalculado[0][3] = "Deducciones";
        System.out.println("Ingrese los Haberes del Empleado");
        while (true) {
            System.out.println("Ingrese el código del ítem");
            int codigo = sc.nextInt();
            if (calculo.verificarCodigo(codigo)) {
                String[] itemEncontrado = calculo.buscarItemEnHaberes(String.valueOf(codigo));
                if (itemEncontrado != null) {
                    System.out.println("item encontrado");
                    System.out.println("codigo " + itemEncontrado[0]);
                    System.out.println("denominacion " + itemEncontrado[1]);
                    System.out.println("valor " + itemEncontrado[2]);
                    break;
                }
                else{
                    System.out.println("El item " + codigo+ " No fue encontrado");
                }

            }


        }
    }
}