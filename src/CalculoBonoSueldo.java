import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CalculoBonoSueldo {
    static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    static ArrayList<BonoSueldo> listaBonos = new ArrayList<BonoSueldo>();
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

    List<Integer> codigosIngresadosHaberes = new ArrayList<>();
    List<Integer> codigosIngresadosDeducciones = new ArrayList<>();


    public boolean verificarCodigoHaberes(int codigo) {
        if (codigosIngresadosHaberes.contains(codigo)) {
            System.out.println("El código ya ha sido cargado, ingrese otro código.");
            return false;
        } else {
            codigosIngresadosHaberes.add(codigo);
            System.out.println("Código ingresado correctamente.");
            return true;
        }
    }
    public boolean verificarCodigoDeducciones(int codigo) {
        if (codigosIngresadosDeducciones.contains(codigo)) {
            System.out.println("El código ya ha sido cargado, ingrese otro código.");
            return false;
        } else {
            codigosIngresadosDeducciones.add(codigo);
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

    public String[] buscarItemEnDeducciones(String codigo) {
        for (String[] item : deducciones) {
            if (item[0].equals(codigo)) {
                return item;
            }
        }
        return null;
    }


    public static void bonoSueldo(){

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
        double montoantiguedad =empleado.calcularAntiguedad();
        String[][] bonoCalculado = new String[10][4];
        bonoCalculado[0][0] = "Codigo Item";
        bonoCalculado[0][1] = "Denominacion";
        bonoCalculado[0][2] = "Haberes";
        bonoCalculado[0][3] = "Deducciones";
        System.out.println("Ingrese los Haberes del Empleado");
        int contadorHaberes = 0;
        int codigo = 0;
        while (true) {
            System.out.println("Ingrese el código del ítem , para salir ingrese 000");
            codigo = sc.nextInt();

            if (codigo==0 && contadorHaberes==0){
                System.out.println("Debe ingresar al menos una deduccion");
                continue;
            }
            else if (codigo ==0 && contadorHaberes!=0){
                break;
            }
            if (calculo.verificarCodigoHaberes(codigo)) {
                String[] itemEncontrado = calculo.buscarItemEnHaberes(String.valueOf(codigo));
                if (itemEncontrado != null) {
                    System.out.println("item encontrado");
                    System.out.println("codigo " + itemEncontrado[0]);
                    System.out.println("denominacion " + itemEncontrado[1]);
                    contadorHaberes++;
                    for (int x = 0; x < 3; x++) {
                        bonoCalculado[contadorHaberes][x] = itemEncontrado[x];
                    }
                    if (bonoCalculado[contadorHaberes][2].equals("9")) {
                        System.out.println("valor " + itemEncontrado[2] + " %");

                        bonoCalculado[contadorHaberes][2] = String.valueOf(empleado.getSueldoBasico() * 0.09);
                    } else {
                        System.out.println("Ingrese el valor correspondiente");
                        int valor = sc.nextInt();
                        bonoCalculado[contadorHaberes][2] = String.valueOf(valor);
                        System.out.println("Valor ingresado correctamente");
                    }
                } else if (codigo == 0 && contadorHaberes != 0) {
                    System.out.println("Carga de haberes finalizada");
                    break;
                } else {
                    System.out.println("El código ingresado es incorrecto");
                    System.out.println("Por favor, ingrese un nuevo codigo valido");
                }

            }
        }
        int contadorDeducciones = 0;
        int contadorDeduccionesArray= contadorHaberes;
        System.out.println("Ingrese las deducciones del empleado");
        while (true) {
            System.out.println("Ingrese el código del ítem");
            codigo = sc.nextInt();
            if (codigo==0 && contadorDeducciones==0){
                System.out.println("Debe ingresar al menos una deduccion");
                continue;
            }
            else if (codigo ==0 && contadorDeducciones!=0){
                break;
            }
            if (calculo.verificarCodigoDeducciones(codigo)) {
                String[] itemEncontrado = calculo.buscarItemEnDeducciones(String.valueOf(codigo));
                if (itemEncontrado != null) {
                    System.out.println("item encontrado");
                    System.out.println("codigo " + itemEncontrado[0]);
                    System.out.println("denominacion " + itemEncontrado[1]);
                    contadorDeducciones++;
                    for (int x = 0; x < 4; x++) {
                        if (x==0 || x==1){
                            bonoCalculado[contadorDeducciones+contadorDeduccionesArray][x] = itemEncontrado[x];
                        }
                        if (x==2){
                            continue;}
                        if (x==3) {
                            bonoCalculado[contadorDeducciones+contadorDeduccionesArray][x] = itemEncontrado[x-1];
                        }
                    }
                    if (!itemEncontrado[2].equals("M")){
                        Float calculoDeduccion= Float.parseFloat(bonoCalculado[contadorDeducciones+contadorDeduccionesArray][3]);
                        bonoCalculado[contadorDeducciones+contadorDeduccionesArray][3]= String.valueOf((empleado.getSueldoBasico()*calculoDeduccion)/100);

                    }
                    else {
                        System.out.println("Ingrese el valor correspondiente");
                        int valor = sc.nextInt();
                        bonoCalculado[contadorDeducciones+contadorDeduccionesArray][3] = String.valueOf(valor);
                        System.out.println("Valor ingresado correctamente");
                    }
                }
                else {
                    System.out.println("El código ingresado es incorrecto");
                    System.out.println("Por favor, ingrese un nuevo codigo valido");
                    if (contadorDeducciones==contadorHaberes){
                        System.out.println("Debe ingresar al menos una deduccion");
                    }
                }
            }
        }
        int sumaHaberes = 0;
        for (int x = 1; x <= contadorHaberes; x++) {
            if (bonoCalculado[x][2] != null) {
                sumaHaberes += (int) Float.parseFloat(bonoCalculado[x][2]);
            }
        }
        int sumaDeducciones = 0;
        for (int x = contadorDeduccionesArray + 1; x <= contadorDeducciones + contadorDeduccionesArray; x++) {
            if (bonoCalculado[x][3] != null) {
                sumaDeducciones += (int) Float.parseFloat(bonoCalculado[x][3]);
            }
        }
        bono.setMontoLiquidacion(empleado.getSueldoBasico()+montoantiguedad+sumaHaberes-sumaDeducciones);
        System.out.println("El monto a liquidar es " + bono.getMontoLiquidacion());

        float subtotalDeducciones = sumaDeducciones;
        float subtotalHaberes = sumaHaberes;
        double NETO = bono.getMontoLiquidacion();


        listaEmpleados.add(empleado);
        listaBonos.add(bono);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empleado empleado = new Empleado();
        System.out.println("Cree un bono de sueldo");
        while (true){
            CalculoBonoSueldo.bonoSueldo();
            System.out.println("Desea cargar otro bono? Ingrese no si desea salir");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("no")){
                break;
            }
        }

        System.out.println("Todos los bonos generados:");
        for (Empleado emp : listaEmpleados) {
            System.out.println("Nombre: " + emp.getNombreEmpleado());
            System.out.println("Cuil: " + emp.getCuil());
            System.out.println("Sueldo basico: " + emp.getSueldoBasico() + "\t" + "\t" + "\t" + "Año de ingreso: " + emp.getAnioIngreso());

        }

    }
}

